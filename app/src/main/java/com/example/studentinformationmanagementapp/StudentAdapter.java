package com.example.studentinformationmanagementapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentinformationmanagementapp.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends ListAdapter<Student, StudentAdapter.StudentViewHolder> {

    protected StudentAdapter() {
        super(new DiffUtil.ItemCallback<Student>() {
            @Override
            public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getName().equals(newItem.getName());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getName().equals(newItem.getName()) &&
                        oldItem.getAge() == newItem.getAge() &&
                        oldItem.getGrade().equals(newItem.getGrade()) &&
                        oldItem.getMajor().equals(newItem.getMajor());
            }
        });
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameText;
        private final TextView ageText;
        private final TextView gradeText;
        private final TextView majorText;

        StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.nameText);
            ageText = itemView.findViewById(R.id.ageText);
            gradeText = itemView.findViewById(R.id.gradeText);
            majorText = itemView.findViewById(R.id.majorText);
        }

        void bind(Student student) {
            nameText.setText(student.getName());
            ageText.setText("Age: " + student.getAge());
            gradeText.setText("Grade: " + student.getGrade());
            majorText.setText("Major: " + student.getMajor());
        }
    }
}


