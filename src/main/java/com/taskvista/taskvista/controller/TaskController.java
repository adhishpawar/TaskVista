package com.taskvista.taskvista.controller;


import com.taskvista.taskvista.dto.TaskDTO;
import com.taskvista.taskvista.dto.TaskResponseDTO;
import com.taskvista.taskvista.entity.Task;
import com.taskvista.taskvista.mapper.TaskMapper;
import com.taskvista.taskvista.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskDTO dto){
        return new ResponseEntity<>(taskService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getTasksByProject(
            @RequestParam Long projectId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size
    ){
        return ResponseEntity.ok(taskService.getByProject(projectId, PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        Task task = taskService.findById(id);
        return ResponseEntity.ok(taskMapper.toResponseDTO(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO dto){
        return ResponseEntity.ok(taskService.updateTask(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
