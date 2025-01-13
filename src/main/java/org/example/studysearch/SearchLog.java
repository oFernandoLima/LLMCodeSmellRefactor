package org.example.studysearch;

import org.example.studycards.CardManager;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.example.studyregistry.StudyMaterial;
import org.example.studyregistry.StudyTaskManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchLog {
    private List<String> searchHistory;
    private Map<String, Integer> searchCount;
    private boolean isLocked;
    private Integer numUsages;
    private String logName;

    public SearchLog(String logName) {
        this.searchHistory = new ArrayList<>();
        this.searchCount = new HashMap<>();
        this.logName = logName;
        this.numUsages = 0;
        this.isLocked = false;
    }

    // Add a search term to the history and increment its count
    public void logSearch(String searchTerm) {
        if (isLocked) {
            throw new IllegalStateException("Cannot log searches when the log is locked.");
        }
        searchHistory.add(searchTerm);
        searchCount.put(searchTerm, searchCount.getOrDefault(searchTerm, 0) + 1);
    }

    // Add search history without incrementing search count (legacy support)
    public void addSearchHistory(String searchTerm) {
        if (isLocked) {
            throw new IllegalStateException("Cannot add to search history when the log is locked.");
        }
        searchHistory.add(searchTerm);
    }

    // Get the most searched term
    public String getMostSearchedTerm() {
        return searchCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // Lock or unlock the log
    public void toggleLock() {
        this.isLocked = !this.isLocked;
    }

    // Get the number of searches for a specific term
    public int getSearchCountForTerm(String term) {
        return searchCount.getOrDefault(term, 0);
    }

    // Increment usage count (used in other classes)
    public void incrementUsage() {
        this.numUsages++;
    }

    public List<String> getSearchHistory() {
        return new ArrayList<>(searchHistory); // Return a copy for immutability
    }

    public String getLogName() {
        return logName;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public Integer getNumUsages() {
        return numUsages;
    }

    public void setNumUsages(int numUsages) {
        this.numUsages = numUsages;
    }

    // Perform a general search across various managers and trackers
    public List<String> performSearch(String text) {
        if (isLocked) {
            throw new IllegalStateException("Search log is locked. Cannot perform search.");
        }
        List<String> results = new ArrayList<>();
        results.addAll(CardManager.getCardManager().searchInCards(text));
        results.addAll(HabitTracker.getHabitTracker().searchInHabits(text));
        results.addAll(TodoTracker.getInstance().searchInTodos(text));
        results.addAll(StudyMaterial.getStudyMaterial().searchInMaterials(text));
        results.addAll(StudyTaskManager.getStudyTaskManager().searchInRegistries(text));
        this.addSearchHistory(text);
        this.numUsages++;
        results.add("\nLogged in: " + this.logName);
        return results;
    }

    public List<String> performMaterialSearch(String text) {
        if (isLocked) {
            throw new IllegalStateException("Search log is locked. Cannot perform search.");
        }
        List<String> results = new ArrayList<>();
        results.addAll(StudyMaterial.getStudyMaterial().searchInMaterials(text));
        this.addSearchHistory(text);
        this.numUsages++;
        results.add("\nLogged in: " + this.logName);
        return results;
    }

    public List<String> performGeneralSearch(String text) {
        if (isLocked) {
            throw new IllegalStateException("Search log is locked. Cannot perform search.");
        }
        List<String> results = new ArrayList<>();
        results.addAll(CardManager.getCardManager().searchInCards(text));
        results.addAll(HabitTracker.getHabitTracker().searchInHabits(text));
        results.addAll(TodoTracker.getInstance().searchInTodos(text));
        results.addAll(StudyMaterial.getStudyMaterial().searchInMaterials(text));
        results.addAll(StudyTaskManager.getStudyTaskManager().searchInRegistries(text));
        this.addSearchHistory(text);
        this.numUsages++;
        results.add("\nLogged in: " + this.logName);
        return results;
    }
}
