package com.taskvista.taskvista.service;

import com.taskvista.taskvista.dto.SprintDTO;
import com.taskvista.taskvista.entity.Sprint;
import com.taskvista.taskvista.mapper.SprintMapper;
import com.taskvista.taskvista.repo.ProjectRepository;
import com.taskvista.taskvista.repo.SprintRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SprintService {

    @Autowired
    private SprintRepository sprintRepo;

    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private SprintMapper sprintMapper;

    @Transactional
    public Sprint create(SprintDTO dto){
        Sprint  s = new Sprint();
        s.setName(dto.name());
        s.setGoal(dto.goal());
        s.setStartDate(dto.startDate());
        s.setEndDate(dto.endDate());
        s.setProject(projectRepo.findById(dto.projectId()).orElseThrow());
        return sprintRepo.save(s);
    }

    public Page<Sprint> getByProject(Long projectId, Pageable pageable)
    {
        return sprintRepo.findByProjectId(projectId,pageable);
    }

    public SprintDTO getById(Long id) {
        Sprint sprint = sprintRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sprint Not Found"));

        return SprintMapper.toDTO(sprintRepo.save(sprint));
    }

    public SprintDTO updateSprint(Long id,SprintDTO dto)
    {
        Sprint sprint = sprintRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sprint Not Found"));

        sprint.setName(dto.name());
        sprint.setStartDate(dto.startDate());
        sprint.setEndDate(dto.endDate());
        sprint.setGoal(dto.goal());

        return SprintMapper.toDTO(sprintRepo.save(sprint));
    }

    public void deleteSprint(Long id) {
        if (!sprintRepo.existsById(id)) {
            throw new EntityNotFoundException("Sprint not found");
        }
        sprintRepo.deleteById(id);
    }

}
