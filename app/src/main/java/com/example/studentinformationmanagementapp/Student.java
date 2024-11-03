package com.example.studentinformationmanagementapp;

public class Student {
    private String name;
    private int age;
    private String grade;
    private String major;

    public Student(String name, int age, String grade, String major) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.major = major;
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGrade() { return grade; }
    public String getMajor() { return major; }
}
