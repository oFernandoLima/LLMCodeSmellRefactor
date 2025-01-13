package org.example.studyregistry;
import org.example.studymaterial.AudioReference;
import org.example.studymaterial.Reference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyMaterial {
    List<Reference> references;
    private static StudyMaterial studyMaterial;
    private Map<String, Integer> referenceCount;

    private StudyMaterial() {
        references = new ArrayList<Reference>();
    }

    public static StudyMaterial getStudyMaterial() {
        if (studyMaterial == null) {
            studyMaterial = new StudyMaterial();
        }
        return studyMaterial;
    }

    public void addReference(Reference ref) {
        references.add(ref);
    }

    List<Reference> getReferences() {
        return references;
    }

    public List<Reference> getTypeReference(Reference type) {
        List<Reference> response = new ArrayList<>();
        for (Reference reference : references) {
            if (reference.getClass() == type.getClass()) {
                response.add(reference);
            }
        }
        return response;
    }

    public void setReferenceCount(Map<String, Integer> referenceCount) {
        this.referenceCount = referenceCount;
    }

    public List<String> searchInMaterials(String text) {
        List<String> response = new ArrayList<>();
        for (Reference reference : references) {
            String mix = (reference.getTitle() != null ? reference.getTitle() : "") + (reference.getDescription() != null ? reference.getDescription() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())) {
                response.add(reference.getTitle());
            }
        }
        return response;
    }

    public Map<String, Integer> getReferenceCountMap() {
        Map<String, Integer> response = initializeReferenceCountMap();
        countReferences(response);
        setReferenceCount(response);
        return response;
    }

    private Map<String, Integer> initializeReferenceCountMap() {
        Map<String, Integer> response = new HashMap<>();
        response.put("Audio References", 0);
        response.put("Video References", 0);
        response.put("Text References", 0);
        return response;
    }

    private void countReferences(Map<String, Integer> response) {
        for (Reference reference : references) {
            if (reference.getClass() == AudioReference.class) {
                incrementCount(response, "Audio References");
            } else if (reference.getClass() == VideoReference.class) {
                handleVideoReference(response, (VideoReference) reference);
            } else if (reference.getClass() == TextReference.class) {
                handleTextReference(response, (TextReference) reference);
            }
        }
    }

    private void handleVideoReference(Map<String, Integer> response, VideoReference videoReference) {
        if (videoReference.handleStreamAvailability()) {
            incrementCount(response, "Video References");
        }
    }

    private void handleTextReference(Map<String, Integer> response, TextReference textReference) {
        if (textReference.handleTextAccess()) {
            incrementCount(response, "Text References");
        }
    }

    private void incrementCount(Map<String, Integer> response, String key) {
        Integer currentCount = response.get(key);
        response.put(key, currentCount + 1);
    }
}
