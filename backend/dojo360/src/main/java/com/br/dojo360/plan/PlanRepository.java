package com.br.dojo360.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, UUID> {
}
