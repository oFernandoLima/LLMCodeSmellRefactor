package org.example.studysearch;

import java.util.List;

public class GeneralSearch implements Search<String> {
    private SearchLog searchLog = new SearchLog("General Search");

    public GeneralSearch() {
    }

    @Override
    public List<String> search(String text) {
        return searchLog.performGeneralSearch(text); // Delegate to SearchLog
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }
}
