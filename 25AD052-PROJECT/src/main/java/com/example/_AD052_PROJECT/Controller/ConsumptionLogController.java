package com.example._AD052_PROJECT.Controller;

import com.example._AD052_PROJECT.Entity.ConsumptionLog;
import com.example._AD052_PROJECT.Service.ConsumptionLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumption-logs")
public class ConsumptionLogController {

    private final ConsumptionLogService consumptionLogService;

    public ConsumptionLogController(
            ConsumptionLogService consumptionLogService) {

        this.consumptionLogService = consumptionLogService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ConsumptionLog> createConsumptionLog(
            @RequestBody ConsumptionLog consumptionLog) {

        return ResponseEntity.ok(
                consumptionLogService.createConsumptionLog(consumptionLog)
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<ConsumptionLog>> getAllConsumptionLogs() {

        return ResponseEntity.ok(
                consumptionLogService.getAllConsumptionLogs()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ConsumptionLog> getConsumptionLogById(
            @PathVariable Long id) {

        return consumptionLogService.getConsumptionLogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ConsumptionLog> updateConsumptionLog(
            @PathVariable Long id,
            @RequestBody ConsumptionLog consumptionLog) {

        return ResponseEntity.ok(
                consumptionLogService.updateConsumptionLog(
                        id,
                        consumptionLog
                )
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsumptionLog(
            @PathVariable Long id) {

        consumptionLogService.deleteConsumptionLog(id);

        return ResponseEntity.noContent().build();
    }
}