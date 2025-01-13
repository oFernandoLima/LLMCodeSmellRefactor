package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudyGoal extends Registry {
    private String goal;
    private List<String> goalRequirements;
    private Boolean isCompleted;
    private LocalDateTime createdDate;
    private Double goalCompletion;
    private StudyObjective studyObjective;
    private StudyPlan studyPlan;
    private String summary;

    public StudyGoal(String name, StudyObjective objective, StudyPlan plan) {
        this.name = name;
        this.studyObjective = objective;
        this.studyPlan = plan;
        goalRequirements = new ArrayList<>();
    }

    public void editActiveCompleted(boolean active, boolean completed) {
        this.isActive = active;
        this.isCompleted = completed;
    }

    public String setGoalSummary() {
        StringBuilder summaryBuilder = new StringBuilder();
        summaryBuilder.append("Goal Summary:\n\n");
        appendActiveGoal(summaryBuilder);
        appendCompletedGoal(summaryBuilder);
        appendGoalRequirements(summaryBuilder);
        appendStudyPlan(summaryBuilder);
        appendStudyObjective(summaryBuilder);
        this.summary = summaryBuilder.toString();
        return this.summary;
    }

    private void appendActiveGoal(StringBuilder summaryBuilder) {
        if (this.isActive) {
            summaryBuilder.append("Active Goal:\n").append(goal).append("\n\n");
        }
    }

    private void appendCompletedGoal(StringBuilder summaryBuilder) {
        if (this.isCompleted) {
            summaryBuilder.append("Completed Goal:\n").append(goal).append("\n\n");
        }
    }

    private void appendGoalRequirements(StringBuilder summaryBuilder) {
        if (this.goalRequirements != null && !this.goalRequirements.isEmpty()) {
            summaryBuilder.append("Requirements:\n");
            for (String requirement : this.goalRequirements) {
                summaryBuilder.append(requirement).append(", ");
            }
            // Remove trailing comma and space
            summaryBuilder.setLength(summaryBuilder.length() - 2);
            summaryBuilder.append("\n\n");
        }
    }

    private void appendStudyPlan(StringBuilder summaryBuilder) {
        if (this.studyPlan != null) {
            summaryBuilder.append("Plan:\n").append(this.studyPlan.toString()).append("\n\n");
        }
    }

    private void appendStudyObjective(StringBuilder summaryBuilder) {
        if (this.studyObjective != null) {
            summaryBuilder.append("Objective:\n").append(this.studyObjective.toString()).append("\n\n");
        }
    }

    public void addRequirement(String requirement) {
        this.goalRequirements.add(requirement);
    }

    public void resetRequirements() {
        this.goalRequirements.clear();
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void toggleIsCompleted() {
        this.isCompleted = !this.isCompleted;
    }

    public LocalDateTime getLimitDate() {
        return createdDate;
    }

    public void setLimitDate(LocalDateTime limitDate) {
        this.createdDate = limitDate;
    }

    public void addDaysLimitDate(int days) {
        this.createdDate = this.createdDate.plusDays(days);
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}
