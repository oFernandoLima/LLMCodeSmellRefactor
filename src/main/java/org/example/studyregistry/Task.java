package org.example.studyregistry;

import java.time.LocalDateTime;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime date;
    private boolean isCompleted;  // Status de conclusão da tarefa

    public Task(String title, String description, String author, LocalDateTime date) {
        this.title = title;
        this.name = title; // Suponho que 'name' seja um campo da classe Registry
        this.description = description;
        this.author = author;
        this.date = date;
        this.isCompleted = false;  // Inicializa a tarefa como não concluída
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    // Novo comportamento: marca a tarefa como concluída
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    // Novo comportamento: marca a tarefa como não concluída
    public void markAsIncomplete() {
        this.isCompleted = false;
    }

    // Novo comportamento: verifica se a tarefa está concluída
    public boolean isCompleted() {
        return isCompleted;
    }

    // Novo comportamento: verifica se a tarefa está atrasada
    public boolean isOverdue() {
        return !isCompleted && LocalDateTime.now().isAfter(date);
    }

    // Novo comportamento: retorna um resumo da tarefa
    public String getTaskSummary() {
        return String.format("Tarefa: %s, Autor: %s, Vencimento: %s, Concluída: %b", title, author, date, isCompleted);
    }

    // Novo comportamento: estende a data de vencimento
    public void extendDeadline(int days) {
        this.date = date.plusDays(days);
    }
}
