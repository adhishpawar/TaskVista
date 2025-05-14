package com.taskvista.taskvista.builder;

import com.taskvista.taskvista.entity.Project;
import com.taskvista.taskvista.entity.Task;

import java.time.LocalDate;
import java.util.List;

public class ProjectBuilder {

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String tenantId;
    private String createdBy;
    private List<Task> tasks;

    public ProjectBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ProjectBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProjectBuilder startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public ProjectBuilder endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public ProjectBuilder tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public ProjectBuilder createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public ProjectBuilder tasks(List<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    public Project build() {
        return new Project(
                null, // ID will be auto-generated
                name,
                description,
                startDate,
                endDate,
                tenantId,
                createdBy,
                tasks
        );
    }
}
