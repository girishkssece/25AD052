package com.example._AD052_PROJECT.Controller;

import com.example._AD052_PROJECT.Entity.GenerationLog;
import com.example._AD052_PROJECT.Service.GenerationLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generation-logs")
public class GenerationLogController {

    private final GenerationLogService generationLogService;

    public GenerationLogController(GenerationLogService generationLogService) {
        this.generationLogService = generationLogService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<GenerationLog> createGenerationLog(
            @RequestBody GenerationLog generationLog) {

        return ResponseEntity.ok(
                generationLogService.createGenerationLog(generationLog)
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<GenerationLog>> getAllGenerationLogs() {

        return ResponseEntity.ok(
                generationLogService.getAllGenerationLogs()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<GenerationLog> getGenerationLogById(
            @PathVariable Long id) {

        return generationLogService.getGenerationLogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<GenerationLog> updateGenerationLog(
            @PathVariable Long id,
            @RequestBody GenerationLog generationLog) {

        return ResponseEntity.ok(
                generationLogService.updateGenerationLog(id, generationLog)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenerationLog(
            @PathVariable Long id) {

        generationLogService.deleteGenerationLog(id);

        return ResponseEntity.noContent().build();
    }
}