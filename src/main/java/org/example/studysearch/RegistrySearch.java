package org.example.studysearch;

import org.example.studycards.CardManager;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.example.studyregistry.StudyTaskManager;

import java.util.ArrayList;
import java.util.List;

public class RegistrySearch implements Search<String> {
    private SearchLog searchLog = new SearchLog("Registry Search");

    public RegistrySearch() {
    }

    @Override
    public List<String> search(String text) {
        return searchLog.performSearch(text); // Delegate to SearchLog
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }
}
