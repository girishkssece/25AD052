package com.example._AD052_PROJECT.Service;

import com.example._AD052_PROJECT.Entity.Household;
import com.example._AD052_PROJECT.Repository.HouseholdRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseholdService {

    private final HouseholdRepository householdRepository;

    public HouseholdService(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    // Create household
    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }

    // Get all households
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    // Get household by ID
    public Optional<Household> getHouseholdById(Long id) {
        return householdRepository.findById(id);
    }

    // Update household
    public Household updateHousehold(Long id, Household updatedHousehold) {

        Household existingHousehold =
                householdRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Household not found with id: " + id));

        existingHousehold.setHouseholdName(
                updatedHousehold.getHouseholdName());

        existingHousehold.setHouseNumber(
                updatedHousehold.getHouseNumber());

        existingHousehold.setAllocationRatio(
                updatedHousehold.getAllocationRatio());

        return householdRepository.save(existingHousehold);
    }

    // Delete household
    public void deleteHousehold(Long id) {

        if (!householdRepository.existsById(id)) {
            throw new RuntimeException(
                    "Household not found with id: " + id);
        }

        householdRepository.deleteById(id);
    }
}
