package com.br.dojo360.mapper;

import com.br.dojo360.person.professor.ProfessorEntity;
import com.br.dojo360.person.professor.dto.CreateProfessor;
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
public class ProfessorMappingConfiguration {

    ModelMapper modelMapper;

    @PostConstruct
    public void doMapping() {
        modelMapper.createTypeMap(CreateProfessor.class, ProfessorEntity.class)
                .setConverter(new AbstractConverter<>() {
                    @Override
                    protected ProfessorEntity convert(CreateProfessor createProfessor) {
                        ProfessorEntity entity;
                        if (createProfessor.uuid() == null) {
                            entity = new ProfessorEntity();
                        } else {
                            entity = new ProfessorEntity(createProfessor.uuid());
                        }
                        entity.setName(createProfessor.name());
                        entity.setCpf(createProfessor.cpf());
                        entity.setGender(createProfessor.gender());
                        entity.setEmail(createProfessor.email());
                        entity.setPhone(createProfessor.phone());
                        entity.setBirthday(createProfessor.birthday());
                        // entity.setAddress(createProfessor.address());
                        entity.setStatus(createProfessor.status());
                        return entity;
                    }
                });

        modelMapper.createTypeMap(ProfessorEntity.class, CreateProfessor.class)
                .setConverter(new AbstractConverter<>() {
                    @Override
                    protected CreateProfessor convert(ProfessorEntity entity) {
                        return new CreateProfessor(
                                entity.getId(),      // UUID uuid
                                entity.getName(),    // String name
                                entity.getCpf(),     // String cpf
                                entity.getGender(),   // char gender
                                entity.getEmail(),    // String email
                                entity.getPhone(),    // String phone
                                entity.getBirthday(),  // LocalDate birthday
                                // Uncomment if you have an Address field
                                // entity.getAddress(),
                                entity.getStatus()    // String status
                        );
                    }
                });
    }
}
