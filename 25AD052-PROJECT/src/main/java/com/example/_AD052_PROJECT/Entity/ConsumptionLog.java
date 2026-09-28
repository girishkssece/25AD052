package com.example._AD052_PROJECT.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "consumption_logs")
@Getter
@Setter
@NoArgsConstructor
public class ConsumptionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long householdId;

    private LocalDate date;

    private Double unitsConsumed;
}