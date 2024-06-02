package org.example;

import java.util.List;

public class Task {
    private int id;
    private String name;
    private String description;
    private List<Integer> completed;

    public Task() {
    }

    public Task(int id, List<Integer> completed, String name, String description) {
        this.id = id;
        this.completed = completed;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public void setId(int taskId) {
        this.id = taskId;
    }

    public List<Integer> getCompleted() {
        return completed;
    }
    public void setCompleted(List<Integer> completed) {
        this.completed = completed;
    }
    public List<Integer> addCompleted(int assigneeId) {
        completed.add(assigneeId);
        return completed;
    }

    public String getName() {
        return name;
    }
    public void setName(String taskName) {
        this.name = taskName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String taskDescription) {
        this.description = taskDescription;
    }
}
