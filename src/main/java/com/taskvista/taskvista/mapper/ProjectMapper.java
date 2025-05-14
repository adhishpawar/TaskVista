package com.taskvista.taskvista.mapper;

import com.taskvista.taskvista.dto.ProjectDTO;
import com.taskvista.taskvista.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public static ProjectDTO toDTO(Project project) {
        return new ProjectDTO(
                project.getName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate()
        );
    }

    public static Project toEntity(ProjectDTO dto) {
        Project project = new Project();
        project.setName(dto.name());
        project.setDescription(dto.description());
        project.setStartDate(dto.startDate());
        project.setEndDate(dto.endDate());
        return project;
    }
}
