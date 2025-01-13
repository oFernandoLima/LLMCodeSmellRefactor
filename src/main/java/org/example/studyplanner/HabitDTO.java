package org.example.studyplanner;

public record HabitDTO(
        String name,
        String motivation,
        Integer dailyMinutesDedication,
        Integer dailyHoursDedication,
        Integer year,
        Integer month,
        Integer day,
        Integer hour,
        Integer minute,
        Integer second,
        Boolean isConcluded
) {}