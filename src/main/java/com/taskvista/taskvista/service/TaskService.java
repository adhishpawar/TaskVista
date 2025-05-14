package com.taskvista.taskvista.service;

import com.taskvista.taskvista.Enum.Status;
import com.taskvista.taskvista.dto.TaskDTO;
import com.taskvista.taskvista.entity.Task;
import com.taskvista.taskvista.mapper.TaskMapper;
import com.taskvista.taskvista.repo.ProjectRepository;
import com.taskvista.taskvista.repo.SprintRepository;
import com.taskvista.taskvista.repo.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

@Service
public class TaskService {


    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private SprintRepository sprintRepo;

    @Autowired
    private TaskMapper taskMapper;

    @Transactional
    public Task create(TaskDTO dto){
        Task t = new Task();
        t.setTitle(dto.title());
        t.setDescription(dto.description());
        t.setStatus(Status.valueOf(String.valueOf(dto.status())));
        t.setAssigneeEmail(dto.assigneeEmail());
        t.setDueDate(dto.dueDate());
        t.setProject(projectRepo.findById(dto.projectId()).orElseThrow());

        if (dto.sprintId() != null) {
            t.setSprint(sprintRepo.findById(dto.sprintId()).orElseThrow());
        }
        return taskRepo.save(t);
    }

    public Page<Task> getByProject(Long projectId, Pageable pageable){
        return taskRepo.findByProjectId(projectId,pageable);
    }

    public Task findById(Long id) {
        return taskRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    public TaskDTO updateTask(Long id, TaskDTO dto) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setDueDate(dto.dueDate());
        task.setAssigneeEmail(dto.assigneeEmail());

        return taskMapper.toDTO(taskRepo.save(task));
    }


    public void deleteTask(Long id) {
        if (!taskRepo.existsById(id)) {
            throw new EntityNotFoundException("Task not found");
        }
        taskRepo.deleteById(id);
    }
}
