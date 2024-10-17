package com.br.dojo360.plan;

import com.br.dojo360.person.student.StudentEntity;
import com.br.dojo360.person.student.dto.CreateStudent;
import com.br.dojo360.plan.dto.CreatePlan;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlanService {

    @Inject
    private PlanRepository planRepository;

    @Inject
    private ModelMapper mapper;

    public PlanEntity findById(UUID uuid) {
        if (Objects.isNull(uuid)) {
            return new PlanEntity();
        }
        Optional<PlanEntity> entityOptional = planRepository.findById(uuid);
        if (entityOptional.isEmpty()) {
            throw new NoSuchElementException("Plano não encontrado.");
        }
        return entityOptional.get();
    }

    public CreatePlan createOrUpdatePlan(CreatePlan newPlan) {
        var planToSave = findById(newPlan.uuid());
        planToSave = mapper.map(newPlan, PlanEntity.class);
        planRepository.save(planToSave);

        return mapper.map(planToSave, CreatePlan.class);
    }
}
