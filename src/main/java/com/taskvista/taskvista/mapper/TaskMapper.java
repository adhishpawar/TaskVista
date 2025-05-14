package com.taskvista.taskvista.mapper;

import com.taskvista.taskvista.dto.TaskDTO;
import com.taskvista.taskvista.dto.TaskResponseDTO;
import com.taskvista.taskvista.entity.Project;
import com.taskvista.taskvista.entity.Sprint;
import com.taskvista.taskvista.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDTO toDTO(Task task) {
        return new TaskDTO(
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getProject().getId(),
                task.getSprint() != null ? task.getSprint().getId() : null,
                task.getAssigneeEmail(),
                task.getDueDate()
        );
    }

    public TaskResponseDTO toResponseDTO(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getProject().getId(),
                task.getProject().getName(),
                task.getSprint() != null ? task.getSprint().getId() : null,
                task.getAssigneeEmail(),
                task.getDueDate()
        );
    }

    public Task toEntity(TaskDTO dto, Project project, Sprint sprint) {
        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setProject(project);
        task.setSprint(sprint);
        task.setAssigneeEmail(dto.assigneeEmail());
        task.setDueDate(dto.dueDate());
        return task;
    }
}
