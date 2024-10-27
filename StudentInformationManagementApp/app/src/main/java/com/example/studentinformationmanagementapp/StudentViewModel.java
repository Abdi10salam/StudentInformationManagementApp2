package com.example.studentinformationmanagementapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.studentinformationmanagementapp.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentViewModel extends ViewModel {
    private final MutableLiveData<List<Student>> students = new MutableLiveData<>();
    private final List<Student> studentList = new ArrayList<>();

    public StudentViewModel() {
        students.setValue(studentList);
    }

    public LiveData<List<Student>> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        studentList.add(student);
        students.setValue(studentList);
    }
}