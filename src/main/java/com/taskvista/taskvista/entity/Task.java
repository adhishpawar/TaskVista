package com.taskvista.taskvista.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taskvista.taskvista.Enum.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated
    private Status status = Status.TODO;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project project;


    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    private String assigneeEmail;
    private LocalDate dueDate;
    private LocalDate createdAt = LocalDate.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public String getAssigneeEmail() {
        return assigneeEmail;
    }

    public void setAssigneeEmail(String assigneeEmail) {
        this.assigneeEmail = assigneeEmail;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
