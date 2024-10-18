package com.br.dojo360.mapper;

import com.br.dojo360.address.AddressData;
import com.br.dojo360.address.AddressEntity;
import com.br.dojo360.person.student.StudentEntity;
import com.br.dojo360.person.student.dto.CreateStudent;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentMappingConfiguration {

    ModelMapper modelMapper;

    @PostConstruct
    public void doMapping() {
        modelMapper.createTypeMap(CreateStudent.class, StudentEntity.class)
                .setConverter(new AbstractConverter<>() {
                    @Override
                    protected StudentEntity convert(CreateStudent createStudent) {
                        StudentEntity entity;
                        if (createStudent.uuid() == null) {
                            entity = new StudentEntity();
                        } else {
                            entity = new StudentEntity(createStudent.uuid());
                        }
                        entity.setName(createStudent.name());
                        entity.setCpf(createStudent.cpf());
                        entity.setBirthday(createStudent.birthday());
                        entity.setGender(createStudent.gender());
                        entity.setEmail(createStudent.email());
                        entity.setPhone(createStudent.phone());
                        entity.setBelts(createStudent.belt());
                        entity.setNFCK(createStudent.nFCK());
                        entity.setNCBK(createStudent.nCBK());
                        entity.setStatus(createStudent.status());
                        entity.setAddress(modelMapper.map(createStudent.address(), AddressEntity.class));
                        return entity;
                    }
                });

        modelMapper.createTypeMap(StudentEntity.class, CreateStudent.class)
                .setConverter(new AbstractConverter<>() {
                    @Override
                    protected CreateStudent convert(StudentEntity entity) {
                        var address = entity.getAddress();
                        //var responsible = entity.getResponsible();
                        return new CreateStudent(
                                entity.getId(),
                                entity.getName(),
                                entity.getCpf(),
                                entity.getBirthday(),
                                entity.getGender(),
                                entity.getEmail(),
                                entity.getPhone(),
                                entity.getBelts(),
                                entity.getNFCK(),
                                entity.getNCBK(),
                                entity.getStatus(),
                                modelMapper.map(address, AddressData.class)
                        );
                    }
                });
    }
}
