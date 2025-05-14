package com.taskvista.taskvista.service;

import com.taskvista.taskvista.builder.ProjectBuilder;
import com.taskvista.taskvista.dto.ProjectDTO;
import com.taskvista.taskvista.entity.Project;
import com.taskvista.taskvista.repo.ProjectRepository;
import com.taskvista.taskvista.tenant.TenantContext;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Temporal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepo;

    @Transactional
    public Project create(ProjectDTO dto, String userEmail){
        String tenantId = TenantContext.getTenantId();

        if (projectRepo.existsByNameAndTenantId(dto.name(), tenantId)) {
            throw new IllegalArgumentException("Project name already exists in your organization.");
        }

        Project p = new ProjectBuilder()
                .name(dto.name())
                .description(dto.description())
                .startDate(dto.startDate())
                .endDate(dto.endDate())
                .createdBy(userEmail)
                .tenantId(tenantId)
                .build();

        return projectRepo.save(p);
    }


    @Transactional
    public Project create(ProjectDTO dto, String tenantId, String userEmail){
        Project p = new Project();
        p.setName(dto.name());
        p.setDescription(dto.description());
        p.setStartDate(dto.startDate());
        p.setEndDate(dto.endDate());
        p.setTenantId(tenantId);
        p.setCreatedBy(userEmail);
        return projectRepo.save(p);
    }

    public Page<Project> getAll(Pageable pageable) {
        String tenantId = TenantContext.getTenantId();
        return projectRepo.findAllByTenantId(tenantId, pageable);
    }


    public Object getById(long id) {
        String tenantId = TenantContext.getTenantId();
        return projectRepo.findByIdAndTenantId(id,tenantId)
                .orElseThrow(()-> new EntityNotFoundException("Project not Found"));
    }

    @Transactional
    public ProjectDTO updateProject(Long id,ProjectDTO dto){
        String tenantId = TenantContext.getTenantId();
        Project project = projectRepo.findByIdAndTenantId(id,tenantId)
                .orElseThrow(() -> new EntityNotFoundException("Project Not Found"));

        project.setName(dto.name());
        project.setDescription(dto.description());
        project.setStartDate(dto.startDate());
        project.setEndDate(dto.endDate());

        projectRepo.save(project);
        return dto;
    }

    @Transactional
    public void deleteProject(Long id){
        String tenantId = TenantContext.getTenantId();
        if(!projectRepo.existsByIdAndTenantId(id,tenantId)){
            throw new EntityNotFoundException("Project not found or already deleted");
        }
        projectRepo.deleteByIdAndTenantId(id,tenantId);
    }


}
