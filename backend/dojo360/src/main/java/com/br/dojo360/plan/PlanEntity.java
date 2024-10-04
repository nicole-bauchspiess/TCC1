package com.br.dojo360.plan;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "plan")
@Table(name ="plan")
public class PlanEntity {

    private UUID id;
    private String description;
    private int weeklyFrequency;
    private int personalFrequency;
    double value;
}
