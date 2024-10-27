package com.br.dojo360.configuration.mapper;

import com.br.dojo360.plan.PlanEntity;
import com.br.dojo360.plan.dto.CreatePlan;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlanMappingConfiguration {


    ModelMapper modelMapper;

    @PostConstruct
    public void doMapping() {
        modelMapper.createTypeMap(CreatePlan.class, PlanEntity.class)
                .setConverter(new AbstractConverter<>() {
                    @Override
                    protected PlanEntity convert(CreatePlan createPlan) {
                        PlanEntity entity;
                        if (createPlan.uuid() == null) {
                            entity = new PlanEntity();
                        } else {
                            entity = new PlanEntity(createPlan.uuid());
                        }
                        entity.setDescription(createPlan.description());
                        entity.setWeeklyFrequency(createPlan.weeklyFrequency());
                        entity.setPersonalFrequency(createPlan.personalFrequency());
                        entity.setValue(createPlan.value());
                        return entity;
                    }
                });

        modelMapper.createTypeMap(PlanEntity.class, CreatePlan.class)
                .setConverter(new AbstractConverter<>() {
                    @Override
                    protected CreatePlan convert(PlanEntity entity) {
                        return new CreatePlan(
                                entity.getId(),
                                entity.getDescription(),
                                entity.getWeeklyFrequency(),
                                entity.getPersonalFrequency(),
                                entity.getValue(),
                                entity.isPersonal()
                        );
                    }
                });
    }
}
