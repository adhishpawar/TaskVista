package com.taskvista.taskvista.repo;

import com.taskvista.taskvista.Enum.Status;
import com.taskvista.taskvista.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByProjectId(Long projectId, Pageable pageable);
    List<Task> findByStatus(Status status);
}
