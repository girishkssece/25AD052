package com.example._AD052_PROJECT.Service;

import com.example._AD052_PROJECT.Entity.GenerationLog;
import com.example._AD052_PROJECT.Repository.GenerationLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenerationLogService {

    private final GenerationLogRepository generationLogRepository;

    public GenerationLogService(GenerationLogRepository generationLogRepository) {
        this.generationLogRepository = generationLogRepository;
    }

    // Create generation log
    public GenerationLog createGenerationLog(GenerationLog generationLog) {
        return generationLogRepository.save(generationLog);
    }

    // Get all generation logs
    public List<GenerationLog> getAllGenerationLogs() {
        return generationLogRepository.findAll();
    }

    // Get generation log by ID
    public Optional<GenerationLog> getGenerationLogById(Long id) {
        return generationLogRepository.findById(id);
    }

    // Update generation log
    public GenerationLog updateGenerationLog(
            Long id,
            GenerationLog updatedGenerationLog) {

        GenerationLog existingGenerationLog =
                generationLogRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Generation log not found with id: " + id));

        existingGenerationLog.setDate(updatedGenerationLog.getDate());

        existingGenerationLog.setUnitsGenerated(
                updatedGenerationLog.getUnitsGenerated());

        return generationLogRepository.save(existingGenerationLog);
    }

    // Delete generation log
    public void deleteGenerationLog(Long id) {

        if (!generationLogRepository.existsById(id)) {
            throw new RuntimeException(
                    "Generation log not found with id: " + id);
        }

        generationLogRepository.deleteById(id);
    }
}
