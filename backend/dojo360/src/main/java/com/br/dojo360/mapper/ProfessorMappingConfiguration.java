package com.br.dojo360.mapper;

import com.br.dojo360.address.AddressData;
import com.br.dojo360.address.AddressEntity;
import com.br.dojo360.person.CreatePerson;
import com.br.dojo360.person.professor.ProfessorEntity;
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
        modelMapper.createTypeMap(CreatePerson.class, ProfessorEntity.class)
                .setConverter(new AbstractConverter<>() {
                    @Override
                    protected ProfessorEntity convert(CreatePerson createProfessor) {
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
                        entity.setAddress(modelMapper.map(createProfessor.address(), AddressEntity.class));
                        entity.setStatus(createProfessor.status());
                        return entity;
                    }
                });

        modelMapper.createTypeMap(ProfessorEntity.class, CreatePerson.class)
                .setConverter(new AbstractConverter<>() {
                    @Override
                    protected CreatePerson convert(ProfessorEntity entity) {
                        var address = entity.getAddress();
                        return new CreatePerson(
                                entity.getId(),
                                entity.getName(),
                                entity.getCpf(),
                                entity.getGender(),
                                entity.getEmail(),
                                entity.getPhone(),
                                entity.getBirthday(),
                                entity.getStatus(),
                                modelMapper.map(address, AddressData.class)
                        );
                    }
                });
    }
}
