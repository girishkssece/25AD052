package com.example._AD052_PROJECT.Service;

import com.example._AD052_PROJECT.Entity.ConsumptionLog;
import com.example._AD052_PROJECT.Repository.ConsumptionLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumptionLogService {

    private final ConsumptionLogRepository consumptionLogRepository;

    public ConsumptionLogService(ConsumptionLogRepository consumptionLogRepository) {
        this.consumptionLogRepository = consumptionLogRepository;
    }

    // Create consumption log
    public ConsumptionLog createConsumptionLog(ConsumptionLog consumptionLog) {
        return consumptionLogRepository.save(consumptionLog);
    }

    // Get all consumption logs
    public List<ConsumptionLog> getAllConsumptionLogs() {
        return consumptionLogRepository.findAll();
    }

    // Get consumption log by ID
    public Optional<ConsumptionLog> getConsumptionLogById(Long id) {
        return consumptionLogRepository.findById(id);
    }

    // Update consumption log
    public ConsumptionLog updateConsumptionLog(
            Long id,
            ConsumptionLog updatedConsumptionLog) {

        ConsumptionLog existingConsumptionLog =
                consumptionLogRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Consumption log not found with id: " + id));

        existingConsumptionLog.setDate(updatedConsumptionLog.getDate());

        existingConsumptionLog.setUnitsConsumed(
                updatedConsumptionLog.getUnitsConsumed());

        return consumptionLogRepository.save(existingConsumptionLog);
    }

    // Delete consumption log
    public void deleteConsumptionLog(Long id) {

        if (!consumptionLogRepository.existsById(id)) {
            throw new RuntimeException(
                    "Consumption log not found with id: " + id);
        }

        consumptionLogRepository.deleteById(id);
    }
}