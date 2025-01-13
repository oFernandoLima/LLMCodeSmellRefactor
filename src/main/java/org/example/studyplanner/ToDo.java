package org.example.studyplanner;

import java.text.MessageFormat;

public class ToDo implements PlannerMaterial {
    private Integer id;
    private String title;
    private String description;
    private int priority;
    private boolean isCompleted;

    public ToDo(Integer id, String title, String description, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}, Completed: {4}]", id, title, description, priority, isCompleted);
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public void resetCompletion() {
        this.isCompleted = false;
    }

    public void updatePriority(int newPriority) {
        if (newPriority < 0) {
            throw new IllegalArgumentException("Priority cannot be negative");
        }
        this.priority = newPriority;
    }

    public String getSummary() {
        return String.format("Task: %s, Priority: %d", title, priority);
    }

    // Moved method
    public String formatInfo() {
        return this.toString() + "\n";
    }
}
