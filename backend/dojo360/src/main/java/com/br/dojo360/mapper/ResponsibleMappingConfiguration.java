package com.br.dojo360.mapper;

import com.br.dojo360.address.AddressData;
import com.br.dojo360.address.AddressEntity;
import com.br.dojo360.person.CreatePerson;
import com.br.dojo360.person.responsible.ResponsibleEntity;
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
public class ResponsibleMappingConfiguration {

    ModelMapper modelMapper;

    @PostConstruct
    public void doMapping() {
        modelMapper.createTypeMap(CreatePerson.class, ResponsibleEntity.class)
                .setConverter(new AbstractConverter<>() {
                    @Override
                    protected ResponsibleEntity convert(CreatePerson createResponsible) {
                        ResponsibleEntity entity;
                        if (createResponsible.uuid() == null) {
                            entity = new ResponsibleEntity();
                        } else {
                            entity = new ResponsibleEntity(createResponsible.uuid());
                        }
                        entity.setName(createResponsible.name());
                        entity.setCpf(createResponsible.cpf());
                        entity.setGender(createResponsible.gender());
                        entity.setEmail(createResponsible.email());
                        entity.setPhone(createResponsible.phone());
                        entity.setBirthday(createResponsible.birthday());
                        entity.setAddress(modelMapper.map(createResponsible.address(), AddressEntity.class));
                        entity.setStatus(createResponsible.status());
                        return entity;
                    }
                });

        modelMapper.createTypeMap(ResponsibleEntity.class, CreatePerson.class)
                .setConverter(new AbstractConverter<>() {
                    @Override
                    protected CreatePerson convert(ResponsibleEntity entity) {
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
