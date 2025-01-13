package org.example.studyregistry;

import java.time.LocalDateTime;

public record StepDetails(
        String firstStep,
        String resetStudyMechanism,
        String consistentStep,
        String seasonalSteps,
        String basicSteps,
        String mainObjectiveTitle,
        String mainGoalTitle,
        String mainMaterialTopic,
        String mainTask,
        Integer numberOfSteps,
        boolean isImportant,
        LocalDateTime startDate,
        LocalDateTime endDate
) {}
