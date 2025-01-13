package org.example.controllers;

import org.example.studymaterial.AudioReference;
import org.example.studymaterial.Reference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;
import org.example.studyregistry.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.controllers.MainController.getInput;
import static org.example.controllers.MainController.validateInput;

public class StudyRegistryController {
    StudyTaskManager studyTaskManager = StudyTaskManager.getStudyTaskManager();
    StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    private Map<String, Runnable> actions = new HashMap<>();

    public StudyRegistryController() {
        assignActions();
    }

    void assignActions(){
        actions.put("1", this::handleAddStudyTask);
        actions.put("2", this::handleAddStudyGoal);
        actions.put("3", this::handleAddStudyMaterial);
        actions.put("4", this::handleAddStudyObjective);
        actions.put("5", this::handleAddStudyPlan);
        actions.put("6", this::handleSetUpWeek);
        actions.put("7", this::handleGetWeekResponsibilities);
    }

    private void handleMethodHeader(String header){
        System.out.println("~~~~" + header + "~~~~\n");
    }

    private Task getStudyTaskInfo(){
        System.out.println("Type the following info: title, description, author \n");
        String title = getInput();
        String description = getInput();
        String author = getInput();
        return new Task(title, description, author, LocalDateTime.now());
    }

    private void handleAddStudyTask(){
        Task task = getStudyTaskInfo();
        studyTaskManager.addRegistry(task);
    }

    // In StudyRegistryController class
    private void handleSetObjective(StudyObjective objective){
        handleMethodHeader("(Study Objective Edit)");
        System.out.println("Type the following info: Integer id, Integer priority " +
                "Integer practicedDays, int day, int month, int year, String name, String title, String description, " +
                "String topic, String objectiveInOneLine, String objectiveFullDescription, String motivation, " +
                "Double duration, boolean isActive  \n");

        // Create DTO from inputs
        StudyObjectiveDTO dto = new StudyObjectiveDTO(
                Integer.parseInt(getInput()), // id
                Integer.parseInt(getInput()), // priority
                Integer.parseInt(getInput()), // practicedDays
                Integer.parseInt(getInput()), // day
                Integer.parseInt(getInput()), // month
                Integer.parseInt(getInput()), // year
                getInput(),                   // name
                getInput(),                   // title
                getInput(),                   // description
                getInput(),                   // topic
                getInput(),                   // objectiveInOneLine
                getInput(),                   // objectiveFullDescription
                getInput(),                   // motivation
                Double.parseDouble(getInput()), // duration
                Boolean.parseBoolean(getInput()) // isActive
        );

        // Pass DTO to the objective's handleSetObjective method
        objective.handleSetObjective(dto);
    }


    private StudyObjective getStudyObjectiveInfo(){
        handleMethodHeader("(Study Objective Creation)");
        System.out.println("Type the following info: title, description \n");
        String title = getInput();
        String description = getInput();
        StudyObjective studyObjective = new StudyObjective(title, description);

        // Create DTO from inputs
        handleSetObjective(studyObjective);
        studyTaskManager.addRegistry(studyObjective);
        return studyObjective;
    }


    private StudyPlan getStudyPlanInfo(){
        handleMethodHeader("(Study Plan Creation)");
        System.out.println("Type the following info: name \n");
        String name = getInput();
        StudyObjective studyObjective = getStudyObjectiveInfo();
        StudyPlan plan = new StudyPlan(name, studyObjective,  new ArrayList<>());
        studyTaskManager.addRegistry(plan);
        return plan;
    }

    private void handleSetSteps(StudyPlan studyPlan) {
        handleMethodHeader("(Study Plan Edit)");
        StepDetails details = buildStepDetails();
        studyPlan.assignSteps(details);
    }

    private StepDetails buildStepDetails() {
        String[] basicStepInputs = collectBasicSteps();
        String[] mainStepInputs = collectMainSteps();
        int numberOfSteps = collectNumberOfSteps();
        boolean isImportant = collectImportance();
        long daysToEnd = collectDaysToEnd();

        return createStepDetails(basicStepInputs, mainStepInputs, numberOfSteps, isImportant, daysToEnd);
    }


    private String[] collectBasicSteps() {
        System.out.println("""
        Type the following basic step details: 
        String firstStep, String resetStudyMechanism, String consistentStep, 
        String seasonalSteps, String basicSteps
        """);
        return new String[]{getInput(), getInput(), getInput(), getInput(), getInput()};
    }

    private String[] collectMainSteps() {
        System.out.println("""
        Type the following main step details: 
        String mainObjectiveTitle, String mainGoalTitle, String mainMaterialTopic, String mainTask
        """);
        return new String[]{getInput(), getInput(), getInput(), getInput()};
    }

    private int collectNumberOfSteps() {
        System.out.println("Type the number of steps:");
        return Integer.parseInt(getInput());
    }

    private boolean collectImportance() {
        System.out.println("Is this step important? (true/false):");
        return Boolean.parseBoolean(getInput());
    }

    private long collectDaysToEnd() {
        System.out.println("Enter the number of days until the end date:");
        return Long.parseLong(getInput());
    }

    private StepDetails createStepDetails(
            String[] basicStepInputs, String[] mainStepInputs,
            int numberOfSteps, boolean isImportant, long daysToEnd
    ) {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime endDate = createdAt.plusDays(daysToEnd);

        return new StepDetails(
                basicStepInputs[0], basicStepInputs[1], basicStepInputs[2], basicStepInputs[3], basicStepInputs[4],
                mainStepInputs[0], mainStepInputs[1], mainStepInputs[2], mainStepInputs[3],
                numberOfSteps, isImportant, createdAt, endDate
        );
    }

    private StudyGoal getStudyGoalInfo(){
        handleMethodHeader("(Study Goal Creation)");
        System.out.println("Type the following info: name \n");
        String name = getInput();
        StudyPlan studyPlan = getStudyPlanInfo();
        handleSetSteps(studyPlan);
        StudyObjective studyObjective = studyPlan.getObjective();
        return new StudyGoal(name, studyObjective, studyPlan);
    }

    private void handleAddStudyGoal(){
        StudyGoal goal = getStudyGoalInfo();
        studyTaskManager.addRegistry(goal);
    }

    private void editAudio(AudioReference audioReference) {
        handleMethodHeader("(Audio Edit)");
        AudioReference.AudioAttributes attributes = collectAudioAttributes();
        audioReference.editAudio(attributes);
    }

    private AudioReference.AudioAttributes collectAudioAttributes() {
        System.out.println("Type the following info: AudioQuality (LOW | MEDIUM | HIGH | VERY_HIGH), " +
                "boolean isDownloadable, String title, String description, String link, String accessRights, " +
                "String license, String language, int rating, int viewCount, int shareCount");

        return new AudioReference.AudioAttributes(
                AudioReference.audioQualityAdapter(getInput()),
                Boolean.parseBoolean(getInput()),
                getInput(), // title
                getInput(), // description
                getInput(), // link
                getInput(), // accessRights
                getInput(), // license
                getInput(), // language
                Integer.parseInt(getInput()), // rating
                Integer.parseInt(getInput()), // viewCount
                Integer.parseInt(getInput())  // shareCount
        );
    }


    private AudioReference addAudioReference(){
        handleMethodHeader("(Audio Reference Creation)");
        System.out.println("Type the following info: Audio Quality ( LOW | MEDIUM | HIGH | VERY_HIGH) \n");
        AudioReference audioReference = new AudioReference(AudioReference.audioQualityAdapter(getInput()));
        editAudio(audioReference);
        return audioReference;
    }

    private VideoReference addVideoReference(){
        handleMethodHeader("(Video Reference Creation)");
        System.out.println("Type the following info: boolean isAvailable, String title, " +
                "String description, String resolution, String frameRate, String videoFormat, String accessRights \n");
        return new VideoReference(Boolean.parseBoolean(getInput()), getInput(), getInput(), getInput(), getInput(),
                getInput(), getInput());
    }

    private TextReference addTextReference(){
        handleMethodHeader("(Text Reference Creation)");
        System.out.println("Type the following info:  String title, String language, int wordCount, String format, String accessRights \n");
        return new TextReference(getInput(), getInput(), Integer.parseInt(getInput()), getInput(),
                getInput());
    }

    private Reference addStudyMaterial(){
        handleMethodHeader("(Study Material Creation)");
        System.out.println("Type the following info: ( AUDIO | VIDEO | TEXT ) \n");
        String type = getInput();
        return switch (type.toLowerCase()) {
            case "audio" -> addAudioReference();
            case "video" -> addVideoReference();
            case "text" -> addTextReference();
            default -> null;
        };
    }

    private void handleAddStudyMaterial(){
        Reference reference = addStudyMaterial();
        if(reference != null){
            studyMaterial.addReference(reference);
        }
    }

    private void handleAddStudyObjective(){
        getStudyObjectiveInfo();
    }

    private void handleAddStudyPlan(){
        getStudyPlanInfo();
        System.out.println("Study Plan Added");
    }

    private String[] collectWeekInputs() {
        String[] inputs = new String[11];
        System.out.println("(Study Task Manager Week Set Up) Type the following info: " +
                "planName, objectiveTitle, objectiveDescription, materialTopic, materialFormat, goal, reminderTitle, " +
                "reminderDescription, mainTaskTitle, mainHabit, mainCardStudy");

        // Collect all required inputs
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = getInput();
        }
        return inputs;
    }


    private void getWeekInfo() {
        // Collect inputs through the helper method
        String[] inputs = collectWeekInputs();

        // Create the WeekPlanDTO using the collected inputs
        WeekPlanDTO weekPlanDTO = new WeekPlanDTO(
                inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5],
                inputs[6], inputs[7], inputs[8], inputs[9], inputs[10]
        );
        studyTaskManager.setUpWeek(weekPlanDTO);
    }



    private void handleSetUpWeek(){
        getWeekInfo();
    }

    private void handleGetWeekResponsibilities(){
        List<String> responsibilities = studyTaskManager.getWeekResponsibilities();
        System.out.println(String.join(", ", responsibilities));
    }

    public void handleRegistryInput(){
        try{
            while(true){
                controllerOptions();
                String response = validateInput(actions);
                if(response == null) {return;}
                actions.get(response).run();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void controllerOptions(){
        System.out.println("""
                0 - return
                1 - add study task
                2 - add study goal
                3 - add study material (audio, video, text)
                4 - add study objective
                5 - add study plan
                6 - set up week
                7 - get week responsibilities
               """);
    }
}
