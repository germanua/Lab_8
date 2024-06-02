package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * ProductsStore class is used to store information about products
 */
public class Database {
    public static final String FILE_NAME = "db.json";

    /**
     * List of courses
     */
    List<Course> data = new ArrayList<Course>();

    /**
     * Default constructor
     */
    public Database() {
        data = readFromFile();
    }

    /**
     * Get list of courses
     *
     * @return list of courses
     */
    public List<Course> getCourses() {
        return data;
    }

    /**
     * Get course by id
     *
     * @param id course id
     * @return course
     */
    public Course getCourseByUid(String id) {
        for (Course course : data) {
            if (Objects.equals(course.getUid(), id)) {
                return course;
            }
        }
        return null;
    }

    /**
     * Get courses for student
     *
     * @param id student id
     * @return course
     */
    public List<Course> getCoursesForStudent(Integer id) {
        List<Course> courses = new ArrayList<>();
        for (Course course : data) {
            if (course.getStudents().contains(id)) {
                courses.add(course);
            }
        }
        return courses;
    }

    /**
     * Get courses for teacher
     *
     * @param id teacher id
     * @return course
     */
    public List<Course> getCoursesForTeacher(Integer id) {
        List<Course> courses = new ArrayList<>();
        for (Course course : data) {
            if (course.getTeachers().contains(id)) {
                courses.add(course);
            }
        }
        return courses;
    }


    /**
     * Add course
     *
     * @param course course
     * @return added course
     */
    public Course addCourse(Course course) {
        Course newCourse = new CourseBuilder(course)
                .setUid(UUID.randomUUID().toString())
                .build();
        data.add(newCourse);
        saveListToFile(data);
        return newCourse;
    }

    /**
     * Add new task
     *
     * @param task     task object
     * @param courseId course uid
     * @return course
     */
    public Course addTask(Task task, String courseId) {
        Course courseToUpdate = getCourseByUid(courseId);
        CourseBuilder courseBuilder = new CourseBuilder(courseToUpdate);
        int highestId = 0;
        for (Task t : courseToUpdate.getTasks()) {
            if (t.getId() > highestId) {
                highestId = t.getId();
            }
        }
        task.setId(highestId + 1);
        List<Task> tasks = courseToUpdate.getTasks();
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
        courseBuilder.setTasks(tasks);
        return updateCourse(courseBuilder.build());
    }

    /**
     * Mark task as done
     *
     * @param courseUid course uid
     * @param taskId    task id
     * @param studentId student id
     * @return course
     */
    public Course markTaskAsDone(String courseUid, int taskId, int studentId) {
        Course courseToUpdate = getCourseByUid(courseUid);
        CourseBuilder courseBuilder = new CourseBuilder(courseToUpdate);
        List<Task> tasks = courseToUpdate.getTasks();
        for (Task task : tasks) {
            if (task.getCompleted() == null) {
                task.setCompleted(new ArrayList<>());
            }
            if (task.getId() == taskId) {
                task.getCompleted().add(studentId);
            }
        }
        courseBuilder.setTasks(tasks);
        return updateCourse(courseBuilder.build());
    }


    /**
     * Update course
     *
     * @param course course
     * @return updated course
     */
    public Course updateCourse(Course course) {
        Integer index = null;
        for (int i = 0; i < data.size(); i++) {
            if (Objects.equals(data.get(i).getUid(), course.getUid())) {
                index = i;
                break;
            }
        }
        if (index == null) {
            throw new RuntimeException("Product not found");
        }
        data.set(index, course);
        saveListToFile(data);
        return course;
    }

    /**
     * Remove course
     *
     * @param uid     course uid
     * @param courses list of courses
     */
    public void removeCourse(String uid, List<Course> courses) {
        data.removeIf(course -> Objects.equals(course.getUid(), uid));
        saveListToFile(data);
    }

    /**
     * Read list of courses from file
     *
     * @return list of courses
     */
    private List<Course> readFromFile() {
        Type REVIEW_TYPE = new TypeToken<List<Course>>() {
        }.getType();
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(FILE_NAME));
        } catch (FileNotFoundException e) {
            saveListToFile(data);
            return data;
        }
        return gson.fromJson(reader, REVIEW_TYPE);
    }

    /**
     * Save list of courses to file
     *
     * @param list list of courses
     */
    private void saveListToFile(List<Course> list) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(list, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
