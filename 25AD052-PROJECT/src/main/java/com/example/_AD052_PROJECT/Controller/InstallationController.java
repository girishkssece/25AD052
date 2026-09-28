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

    public InstallationController(
            InstallationService installationService) {

        this.installationService = installationService;
    }

    // Create installation
    @PostMapping
    public ResponseEntity<Installation> createInstallation(
            @RequestBody Installation installation) {

        return ResponseEntity.ok(
                installationService.createInstallation(installation)
        );
    }

    // Get all installations
    @GetMapping
    public ResponseEntity<List<Installation>> getAllInstallations() {

        return ResponseEntity.ok(
                installationService.getAllInstallations()
        );
    }

    // Get installation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Installation> getInstallationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                installationService.getInstallationById(id)
        );
    }

    // Update installation
    @PutMapping("/{id}")
    public ResponseEntity<Installation> updateInstallation(
            @PathVariable Long id,
            @RequestBody Installation installation) {

        return ResponseEntity.ok(
                installationService.updateInstallation(
                        id,
                        installation
                )
        );
    }

    // Delete installation
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInstallation(
            @PathVariable Long id) {

        installationService.deleteInstallation(id);

        return ResponseEntity.ok(
                "Installation deleted successfully"
        );
    }
}