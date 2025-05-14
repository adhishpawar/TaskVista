package com.taskvista.taskvista.controller;

import com.taskvista.taskvista.dto.SprintDTO;
import com.taskvista.taskvista.service.SprintService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sprints")
public class sprintController {

    @Autowired
    private SprintService sprintService;

    @PostMapping
    public ResponseEntity<?> createSprint(@Valid @RequestBody SprintDTO dto){
        return new ResponseEntity<>(sprintService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getSprintsByProject(
            @RequestParam Long projectId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size
    ){
        return ResponseEntity.ok(sprintService.getByProject(projectId, PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSprintById(@PathVariable Long id) {
        return ResponseEntity.ok(sprintService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSprint(@PathVariable Long id, @Valid @RequestBody SprintDTO dto) {
        return ResponseEntity.ok(sprintService.updateSprint(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSprint(@PathVariable Long id) {
        sprintService.deleteSprint(id);
        return ResponseEntity.noContent().build();
    }
}
