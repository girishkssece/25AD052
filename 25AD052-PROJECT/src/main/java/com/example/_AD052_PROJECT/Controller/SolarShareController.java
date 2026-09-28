package com.example._AD052_PROJECT.Controller;

import com.example._AD052_PROJECT.Service.SolarShareService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/solar-share")
public class SolarShareController {

    private final SolarShareService solarShareService;

    public SolarShareController(SolarShareService solarShareService) {
        this.solarShareService = solarShareService;
    }

    @GetMapping("/validate-allocation")
    public ResponseEntity<Map<String, Object>> validateAllocation(
            @RequestParam LocalDate date) {

        solarShareService.validateAllocationForDay(date);

        return ResponseEntity.ok(
                Map.of(
                        "date", date,
                        "status", "VALID",
                        "message", "Total allocated units do not exceed total generated units"
                )
        );
    }

    @GetMapping("/net-export/{householdId}")
    public ResponseEntity<Map<String, Object>> calculateNetExport(
            @PathVariable Long householdId,
            @RequestParam LocalDate date) {

        double netExport =
                solarShareService.calculateNetExport(householdId, date);

        return ResponseEntity.ok(
                Map.of(
                        "householdId", householdId,
                        "date", date,
                        "netExport", netExport
                )
        );
    }
    @GetMapping("/monthly-summary/{householdId}")
    public ResponseEntity<Map<String, Object>> getMonthlySummary(
            @PathVariable Long householdId,
            @RequestParam int year,
            @RequestParam int month) {

        Map<String, Object> summary =
                solarShareService.getMonthlySummary(
                        householdId,
                        year,
                        month
                );

        return ResponseEntity.ok(summary);
    }
}