package org.example;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Course {
    private String uid;
    private String name;
    private List<Integer> students;
    private List<Integer> teachers;
    private List<Task> tasks;

    /**
     * Default constructor
     */
    public Course() {
    }

    /**
     * Constructor
     *
     * @param uid         course id
     * @param name        course name
     * @param students    list of students
     * @param teachers    list of teachers
     * @param tasks list of assignments
     */
    public Course(String uid, String name, List<Integer> students, List<Integer> teachers, List<Task> tasks) {
        this.uid = uid;
        this.name = name;
        this.students = students;
        this.teachers = teachers;
        this.tasks = tasks;
    }

    /**
     * Get course id
     *
     * @return course id
     */
    @SerializedName("uid")
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    @SerializedName("name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @SerializedName("students")
    public List<Integer> getStudents() {
        return students;
    }
    public void setStudents(List<Integer> students) {
        this.students = students;
    }
    @SerializedName("teachers")
    public List<Integer> getTeachers() {
        return teachers;
    }
    public void setTeachers(List<Integer> teachers) {
        this.teachers = teachers;
    }
    @SerializedName("tasks")
    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
