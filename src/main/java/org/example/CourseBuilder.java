package org.example;

import java.util.List;

/**
 * ProductBuilder class is used to create Product objects
 */
public class CourseBuilder {
    private String uid;
    private String name;
    private List<Integer> students;
    private List<Integer> teachers;
    private List<Task> tasks;

    /**
     * Default constructor
     */
    public CourseBuilder() {}

    /**
     * Constructor
     *
     * @param uid course id
     * @param name course name
     * @param students list of students
     * @param teachers list of teachers
     * @param tasks list of assignments
     */
    public CourseBuilder(String uid, String name, List<Integer> students, List<Integer> teachers, List<Task> tasks) {
        this.uid = uid;
        this.name = name;
        this.students = students;
        this.teachers = teachers;
        this.tasks = tasks;
    }

    /**
     * Constructor
     *
     * @param course Course object
     */
    public CourseBuilder(Course course) {
        this.uid = course.getUid();
        this.name = course.getName();
        this.students = course.getStudents();
        this.teachers = course.getTeachers();
        this.tasks = course.getTasks();
    }


    /**
     * Set course id
     *
     * @param uid course id
     * @return CourseBuilder object
     */
    public CourseBuilder setUid(String uid) {
        this.uid = uid;
        return this;
    }

    /**
     * Set course name
     *
     * @param name course name
     * @return CourseBuilder object
     */
    public CourseBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set list of students
     *
     * @param students list of students
     * @return CourseBuilder object
     */
    public CourseBuilder setStudents(List<Integer> students) {
        this.students = students;
        return this;
    }

    /**
     * Set list of teachers
     *
     * @param teachers list of teachers
     * @return CourseBuilder object
     */
    public CourseBuilder setTeachers(List<Integer> teachers) {
        this.teachers = teachers;
        return this;
    }

    /**
     * Set list of assignments
     *
     * @param tasks list of assignments
     * @return CourseBuilder object
     */
    public CourseBuilder setTasks(List<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    /**
     * Build Course object
     *
     * @return Course object
     */
    public Course build() {
        return new Course(uid, name, students, teachers, tasks);
    }
}
