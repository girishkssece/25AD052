package com.example._AD052_PROJECT.Controller;

import com.example._AD052_PROJECT.Entity.Installation;
import com.example._AD052_PROJECT.Service.InstallationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/installations")
public class InstallationController {

    private final InstallationService installationService;

    public InstallationController(InstallationService installationService) {
        this.installationService = installationService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Installation> createInstallation(
            @RequestBody Installation installation) {

        return ResponseEntity.ok(
                installationService.createInstallation(installation)
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Installation>> getAllInstallations() {

        return ResponseEntity.ok(
                installationService.getAllInstallations()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Installation> getInstallationById(
            @PathVariable Long id) {

        return installationService.getInstallationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Installation> updateInstallation(
            @PathVariable Long id,
            @RequestBody Installation installation) {

        return ResponseEntity.ok(
                installationService.updateInstallation(id, installation)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstallation(
            @PathVariable Long id) {

        installationService.deleteInstallation(id);

        return ResponseEntity.noContent().build();
    }
}