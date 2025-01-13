package org.example.studycards;

public class Card {
    private String question;
    private String answer;

    public Card(String question, String answer) {
        validateInput(question, answer);
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        validateInput(question, this.answer); // Ensure the question is valid
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        validateInput(this.question, answer);
        this.answer = answer;
    }

    public void edit(String question, String answer) {
        validateInput(question, answer);
        this.question = question;
        this.answer = answer;
    }

    private void validateInput(String question, String answer) {
        if (isNullOrEmpty(question) || isNullOrEmpty(answer)) {
            throw new IllegalArgumentException("Question and answer must not be null or empty.");
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "Card{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
