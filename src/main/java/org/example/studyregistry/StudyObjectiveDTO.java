package org.example.studyregistry;

import java.time.LocalDate;

public record StudyObjectiveDTO(
        Integer id,
        Integer priority,
        Integer practicedDays,
        int day,
        int month,
        int year,
        String name,
        String title,
        String description,
        String topic,
        String objectiveInOneLine,
        String objectiveFullDescription,
        String motivation,
        Double duration,
        boolean isActive) {}
