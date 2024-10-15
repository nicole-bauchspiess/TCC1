package com.br.dojo360.mapper;

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
                        var entity = new StudentEntity();
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
                        return entity;
                    }
                });

        modelMapper.createTypeMap(StudentEntity.class, CreateStudent.class)
                .setConverter(new AbstractConverter<>() {
                    @Override
                    protected CreateStudent convert(StudentEntity entity) {
                        return new CreateStudent(
                                entity.getName(),
                                entity.getCpf(),
                                entity.getBirthday(),
                                entity.getGender(),
                                entity.getEmail(),
                                entity.getPhone(),
                                entity.getBelts(),
                                entity.getNFCK(),
                                entity.getNCBK(),
                                entity.getStatus()
                        );
                    }
                });
    }
}
