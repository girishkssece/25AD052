package com.example._AD052_PROJECT.Service;

import com.example._AD052_PROJECT.Entity.Installation;
import com.example._AD052_PROJECT.Repository.InstallationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InstallationService {

    private final InstallationRepository installationRepository;

    public InstallationService(InstallationRepository installationRepository) {
        this.installationRepository = installationRepository;
    }

    // Create installation
    public Installation createInstallation(Installation installation) {
        return installationRepository.save(installation);
    }

    // Get all installations
    public List<Installation> getAllInstallations() {
        return installationRepository.findAll();
    }

    // Get installation by ID
    public Installation getInstallationById(Long id) {

        return installationRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Installation not found with id: " + id
                        )
                );
    }

    // Update installation
    public Installation updateInstallation(
            Long id,
            Installation installation) {

        Installation existingInstallation =
                installationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Installation not found with id: " + id
                                )
                        );

        existingInstallation.setName(installation.getName());
        existingInstallation.setLocation(installation.getLocation());
        existingInstallation.setTotalCapacity(
                installation.getTotalCapacity()
        );

        return installationRepository.save(existingInstallation);
    }

    // Delete installation
    public void deleteInstallation(Long id) {

        Installation existingInstallation =
                installationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Installation not found with id: " + id
                                )
                        );

        installationRepository.delete(existingInstallation);
    }
}