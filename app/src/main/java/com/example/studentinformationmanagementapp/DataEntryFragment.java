package com.example.studentinformationmanagementapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import org.jetbrains.annotations.Nullable;

public class DataEntryFragment extends Fragment {
    private EditText nameInput, ageInput, gradeInput, majorInput;
    private Button submitButton, clearButton;
    private StudentViewModel studentViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentViewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_entry, container, false);

        // Initialize views
        nameInput = view.findViewById(R.id.nameInput);
        ageInput = view.findViewById(R.id.ageInput);
        gradeInput = view.findViewById(R.id.gradeInput);
        majorInput = view.findViewById(R.id.majorInput);
        submitButton = view.findViewById(R.id.submitButton);
        clearButton = view.findViewById(R.id.clearButton);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start animation for the button
                startAnimation(v);

                if (validateInput()) {
                    Student student = new Student(
                            nameInput.getText().toString(),
                            Integer.parseInt(ageInput.getText().toString()),
                            gradeInput.getText().toString(),
                            majorInput.getText().toString()
                    );
                    studentViewModel.addStudent(student);
                    clearFields();

                    if (getResources().getConfiguration().orientation ==
                            Configuration.ORIENTATION_PORTRAIT) {
                        getParentFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new DisplayFragment())
                                .addToBackStack(null)
                                .commit();
                    }
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start animation for the button
                startAnimation(v);
                clearFields();
            }
        });
    }

    private void startAnimation(View view) {
        Animation scaleIn = AnimationUtils.loadAnimation(getContext(), R.anim.scale_in);
        Animation scaleOut = AnimationUtils.loadAnimation(getContext(), R.anim.scale_out);
        view.startAnimation(scaleIn);
        scaleIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // No action needed here
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.startAnimation(scaleOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // No action needed here
            }
        });
    }

    private boolean validateInput() {
        boolean isValid = true;

        if (TextUtils.isEmpty(nameInput.getText())) {
            nameInput.setError("Name is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(ageInput.getText())) {
            ageInput.setError("Age is required");
            isValid = false;
        } else {
            try {
                int age = Integer.parseInt(ageInput.getText().toString());
                if (age < 0 || age > 120) {
                    ageInput.setError("Invalid age");
                    isValid = false;
                }
            } catch (NumberFormatException e) {
                ageInput.setError("Invalid age format");
                isValid = false;
            }
        }

        if (TextUtils.isEmpty(gradeInput.getText())) {
            gradeInput.setError("Grade is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(majorInput.getText())) {
            majorInput.setError("Major is required");
            isValid = false;
        }

        return isValid;
    }

    private void clearFields() {
        nameInput.setText("");
        ageInput.setText("");
        gradeInput.setText("");
        majorInput.setText("");
    }
}
