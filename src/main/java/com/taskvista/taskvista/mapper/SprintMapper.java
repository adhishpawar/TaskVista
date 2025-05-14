package com.taskvista.taskvista.mapper;

import com.taskvista.taskvista.dto.SprintDTO;
import com.taskvista.taskvista.entity.Project;
import com.taskvista.taskvista.entity.Sprint;
import org.springframework.stereotype.Component;

@Component
public class SprintMapper {

    public static SprintDTO toDTO(Sprint sprint) {
        return new SprintDTO(
                sprint.getName(),
                sprint.getGoal(),
                sprint.getStartDate(),
                sprint.getEndDate(),
                sprint.getProject().getId()
        );
    }

    public static Sprint toEntity(SprintDTO dto, Project project) {
        Sprint sprint = new Sprint();
        sprint.setName(dto.name());
        sprint.setGoal(dto.goal());
        sprint.setStartDate(dto.startDate());
        sprint.setEndDate(dto.endDate());
        sprint.setProject(project);
        return sprint;
    }
}
