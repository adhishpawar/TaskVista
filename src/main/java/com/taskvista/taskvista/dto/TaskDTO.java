package com.taskvista.taskvista.dto;

import java.time.LocalDate;

public record TaskDTO(String title, String description, com.taskvista.taskvista.Enum.Status status, Long projectId, Long sprintId, String assigneeEmail, LocalDate dueDate) {
}
