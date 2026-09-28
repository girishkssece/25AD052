package com.example._AD052_PROJECT.Service;

import com.example._AD052_PROJECT.Entity.ConsumptionLog;
import com.example._AD052_PROJECT.Entity.GenerationLog;
import com.example._AD052_PROJECT.Entity.Household;
import com.example._AD052_PROJECT.Repository.ConsumptionLogRepository;
import com.example._AD052_PROJECT.Repository.GenerationLogRepository;
import com.example._AD052_PROJECT.Repository.HouseholdRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
@Service
public class SolarShareService {

    private final HouseholdRepository householdRepository;
    private final GenerationLogRepository generationLogRepository;
    private final ConsumptionLogRepository consumptionLogRepository;

    public SolarShareService(
            HouseholdRepository householdRepository,
            GenerationLogRepository generationLogRepository,
            ConsumptionLogRepository consumptionLogRepository) {

        this.householdRepository = householdRepository;
        this.generationLogRepository = generationLogRepository;
        this.consumptionLogRepository = consumptionLogRepository;
    }

    public double calculateNetExport(Long householdId, LocalDate date) {

        // 1. Find household
        Household household = householdRepository.findById(householdId)
                .orElseThrow(() ->
                        new RuntimeException("Household not found with id: " + householdId));

        // 2. Find generation for the given date
        GenerationLog generationLog = generationLogRepository.findAll()
                .stream()
                .filter(log -> log.getDate().equals(date))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Generation log not found for date: " + date));

        // 3. Calculate household's generation share
        double generationShare =
                generationLog.getUnitsGenerated()
                        * household.getAllocationRatio();

        // 4. Find household consumption for that date
        double consumption = consumptionLogRepository.findAll()
                .stream()
                .filter(log ->
                        householdId.equals(log.getHouseholdId())
                                && log.getDate().equals(date))
                .mapToDouble(ConsumptionLog::getUnitsConsumed)
                .sum();

        // 5. Calculate net export
        double netExport = generationShare - consumption;

        // 6. Export cannot be negative
        return Math.max(netExport, 0);
    }
    public void validateAllocationForDay(LocalDate date) {

        // 1. Find the generation for the given date
        GenerationLog generationLog = generationLogRepository.findAll()
                .stream()
                .filter(log -> log.getDate().equals(date))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(
                                "Generation log not found for date: " + date));

        // 2. Get total generation
        double totalGeneration = generationLog.getUnitsGenerated();

        // 3. Get all households
        List<Household> households = householdRepository.findAll();

        // 4. Calculate total allocation ratio
        double totalAllocationRatio = households.stream()
                .mapToDouble(Household::getAllocationRatio)
                .sum();

        // 5. Calculate total allocated units
        double totalAllocatedUnits =
                totalGeneration * totalAllocationRatio;

        // 6. Check the business rule
        if (totalAllocatedUnits > totalGeneration) {
            throw new RuntimeException(
                    "Total allocated units cannot exceed total generated units");
        }
    }
    public Map<String, Object> getMonthlySummary(
            Long householdId,
            int year,
            int month) {

        // 1. Find the household
        Household household = householdRepository.findById(householdId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Household not found with id: " + householdId));

        // 2. Get all generation logs
        List<GenerationLog> generationLogs =
                generationLogRepository.findAll();

        // 3. Calculate total generation share for the month
        double monthlyGenerationShare = generationLogs.stream()
                .filter(log ->
                        log.getDate().getYear() == year
                                && log.getDate().getMonthValue() == month)
                .mapToDouble(log ->
                        log.getUnitsGenerated()
                                * household.getAllocationRatio())
                .sum();

        // 4. Get all consumption logs
        List<ConsumptionLog> consumptionLogs =
                consumptionLogRepository.findAll();

        // 5. Calculate total household consumption for the month
        double monthlyConsumption = consumptionLogs.stream()
                .filter(log ->
                        householdId.equals(log.getHouseholdId())
                                && log.getDate().getYear() == year
                                && log.getDate().getMonthValue() == month)
                .mapToDouble(ConsumptionLog::getUnitsConsumed)
                .sum();

        // 6. Calculate monthly net export
        double monthlyNetExport =
                Math.max(
                        monthlyGenerationShare - monthlyConsumption,
                        0
                );

        // 7. Prepare response
        return Map.of(
                "householdId", householdId,
                "year", year,
                "month", month,
                "monthlyGenerationShare", monthlyGenerationShare,
                "monthlyConsumption", monthlyConsumption,
                "monthlyNetExport", monthlyNetExport
        );
    }
}
