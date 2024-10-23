package com.br.dojo360.plan;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Entity(name = "plan")
@Table(name = "plan")
public class PlanEntity {

    @Id
    private UUID id;
    private String description;
    private int weeklyFrequency;
    private int personalFrequency;
    private double value;

    public PlanEntity() {
        this.id = UUID.randomUUID();
    }

    public PlanEntity(UUID id) {
        this.setId(id);
    }

}
