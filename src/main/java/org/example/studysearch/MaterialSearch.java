package org.example.studysearch;

import java.util.List;

public class MaterialSearch implements Search<String> {

    private SearchLog searchLog = new SearchLog("Material Search");

    public MaterialSearch() {}

    @Override
    public List<String> search(String text) {
        return searchLog.performMaterialSearch(text); // Delegate to SearchLog
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }
}
