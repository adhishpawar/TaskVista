package com.taskvista.taskvista.dto;

import com.taskvista.taskvista.Enum.Status;
import java.time.LocalDate;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        Status status,
        Long projectId,
        String projectName,
        Long sprintId,
        String assigneeEmail,
        LocalDate dueDate
) {}
