package com.example._AD052_PROJECT.Service;

import com.example._AD052_PROJECT.Entity.Installation;
import com.example._AD052_PROJECT.Repository.InstallationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Installation> getInstallationById(Long id) {
        return installationRepository.findById(id);
    }

    // Update installation
    public Installation updateInstallation(Long id, Installation updatedInstallation) {

        Installation existingInstallation =
                installationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Installation not found with id: " + id));

        existingInstallation.setName(updatedInstallation.getName());
        existingInstallation.setLocation(updatedInstallation.getLocation());
        existingInstallation.setTotalCapacity(updatedInstallation.getTotalCapacity());

        return installationRepository.save(existingInstallation);
    }

    // Delete installation
    public void deleteInstallation(Long id) {

        if (!installationRepository.existsById(id)) {
            throw new RuntimeException("Installation not found with id: " + id);
        }

        installationRepository.deleteById(id);
    }
}