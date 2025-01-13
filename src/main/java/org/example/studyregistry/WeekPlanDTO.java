package org.example.studyregistry;

public class WeekPlanDTO {
    private String planName;
    private String objectiveTitle;
    private String objectiveDescription;
    private String materialTopic;
    private String materialFormat;
    private String goal;
    private String reminderTitle;
    private String reminderDescription;
    private String mainTaskTitle;
    private String mainHabit;
    private String mainCardStudy;

    // Constructor
    public WeekPlanDTO(String planName, String objectiveTitle, String objectiveDescription, String materialTopic,
                       String materialFormat, String goal, String reminderTitle, String reminderDescription,
                       String mainTaskTitle, String mainHabit, String mainCardStudy) {
        this.planName = planName;
        this.objectiveTitle = objectiveTitle;
        this.objectiveDescription = objectiveDescription;
        this.materialTopic = materialTopic;
        this.materialFormat = materialFormat;
        this.goal = goal;
        this.reminderTitle = reminderTitle;
        this.reminderDescription = reminderDescription;
        this.mainTaskTitle = mainTaskTitle;
        this.mainHabit = mainHabit;
        this.mainCardStudy = mainCardStudy;
    }

    // Getters (and setters if needed)
    public String getPlanName() {
        return planName;
    }

    public String getObjectiveTitle() {
        return objectiveTitle;
    }

    public String getObjectiveDescription() {
        return objectiveDescription;
    }

    public String getMaterialTopic() {
        return materialTopic;
    }

    public String getMaterialFormat() {
        return materialFormat;
    }

    public String getGoal() {
        return goal;
    }

    public String getReminderTitle() {
        return reminderTitle;
    }

    public String getReminderDescription() {
        return reminderDescription;
    }

    public String getMainTaskTitle() {
        return mainTaskTitle;
    }

    public String getMainHabit() {
        return mainHabit;
    }

    public String getMainCardStudy() {
        return mainCardStudy;
    }
}
