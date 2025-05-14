package com.taskvista.taskvista.controller;


import com.taskvista.taskvista.dto.ProjectDTO;
import com.taskvista.taskvista.entity.Project;
import com.taskvista.taskvista.service.ProjectService;
import com.taskvista.taskvista.tenant.TenantContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<?> createProject(@Valid @RequestBody ProjectDTO dto,@RequestParam String userEmail)
    {

        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.create(dto,userEmail));
    }

    @GetMapping
    public ResponseEntity<?> getProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
    {
        return ResponseEntity.ok(projectService.getAll(PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable long id){
        return ResponseEntity.ok(projectService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectDTO dto){

        return ResponseEntity.ok(projectService.updateProject(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build(); // 204 No Content

    }
}
