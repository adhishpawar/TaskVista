package com.taskvista.taskvista.repo;

import com.taskvista.taskvista.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    //Ensure all query methods are tenant-scoped.

    Page<Project> findAllByTenantId(String tenantId, Pageable pageable);
    Optional<Project> findByIdAndTenantId(Long id, String tenantId);
    boolean existsByIdAndTenantId(Long id, String tenantId);
    void deleteByIdAndTenantId(Long id, String tenantId);
    boolean existsByNameAndTenantId(String name, String tenantId);
}
