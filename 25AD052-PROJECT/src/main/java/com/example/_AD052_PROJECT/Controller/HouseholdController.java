package com.example._AD052_PROJECT.Controller;

import com.example._AD052_PROJECT.Entity.Household;
import com.example._AD052_PROJECT.Service.HouseholdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/households")
public class HouseholdController {

    private final HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Household> createHousehold(
            @RequestBody Household household) {

        return ResponseEntity.ok(
                householdService.createHousehold(household)
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Household>> getAllHouseholds() {

        return ResponseEntity.ok(
                householdService.getAllHouseholds()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Household> getHouseholdById(
            @PathVariable Long id) {

        return householdService.getHouseholdById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Household> updateHousehold(
            @PathVariable Long id,
            @RequestBody Household household) {

        return ResponseEntity.ok(
                householdService.updateHousehold(id, household)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHousehold(
            @PathVariable Long id) {

        householdService.deleteHousehold(id);

        return ResponseEntity.noContent().build();
    }
}
