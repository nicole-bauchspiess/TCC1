package com.br.dojo360.plan.dto;

import java.util.UUID;

public record CreatePlan(
        UUID uuid,
        String description,
        int weeklyFrequency,
        int personalFrequency,
        double value,
        boolean personal
) {
}
