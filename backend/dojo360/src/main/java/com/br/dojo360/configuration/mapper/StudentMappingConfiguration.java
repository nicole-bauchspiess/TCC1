package com.br.dojo360.configuration.mapper;

import com.br.dojo360.address.AddressData;
import com.br.dojo360.address.AddressEntity;
import com.br.dojo360.person.CreatePerson;
import com.br.dojo360.person.responsible.ResponsibleEntity;
import com.br.dojo360.person.student.StudentEntity;
import com.br.dojo360.person.student.dto.CreateStudentDTO;
import com.br.dojo360.person.student.dto.ListStudentDTO;
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
        modelMapper.createTypeMap(CreateStudentDTO.class, StudentEntity.class)
                .setConverter(new AbstractConverter<>() {
                    @Override
                    protected StudentEntity convert(CreateStudentDTO createStudent) {
                        StudentEntity entity;
                        if (createStudent.getUuid() == null) {
                            entity = new StudentEntity();
                        } else {
                            entity = new StudentEntity(createStudent.getUuid());
                        }
                        entity.setName(createStudent.getName());
                        entity.setCpf(createStudent.getCpf());
                        entity.setBirthday(createStudent.getBirthday());
                        entity.setGender(createStudent.getGender());
                        entity.setEmail(createStudent.getEmail());
                        entity.setPhone(createStudent.getPhone());
                        entity.setBelts(createStudent.getBelt());
                        entity.setNFCK(createStudent.getNFCK());
                        entity.setNCBK(createStudent.getNCBK());
                        entity.setIsEnable(true);
                        if (createStudent.getAddress() != null) {
                            entity.setAddress(modelMapper.map(createStudent.getAddress(), AddressEntity.class));
                        }
                        if (createStudent.getResponsible() != null) {
                            entity.setResponsible(modelMapper.map(createStudent.getResponsible(), ResponsibleEntity.class));
                        }
                        return entity;
                    }
                });

        modelMapper.createTypeMap(StudentEntity.class, CreateStudentDTO.class)
                .setConverter(new AbstractConverter<>() {
                    @Override
                    protected CreateStudentDTO convert(StudentEntity entity) {

                        CreateStudentDTO createStudent = new CreateStudentDTO();
                        createStudent.setUuid(entity.getId());
                        createStudent.setName(entity.getName());
                        createStudent.setCpf(entity.getCpf());
                        createStudent.setBirthday(entity.getBirthday());
                        createStudent.setGender(entity.getGender());
                        createStudent.setEmail(entity.getEmail());
                        createStudent.setPhone(entity.getPhone());
                        createStudent.setBelt(entity.getBelts());
                        createStudent.setNFCK(entity.getNFCK());
                        createStudent.setNCBK(entity.getNCBK());
                        createStudent.setEnable(entity.getIsEnable());

                        if (entity.getAddress() != null) {
                            createStudent.setAddress(modelMapper.map(entity.getAddress(), AddressData.class));
                        }

                        if (entity.getResponsible() != null) {
                            createStudent.setResponsible(modelMapper.map(entity.getResponsible(), CreatePerson.class));
                        }
                        return createStudent;
                    }
                });

        modelMapper.createTypeMap(StudentEntity.class, ListStudentDTO.class)
                .setConverter(new AbstractConverter<>() {
                    @Override
                    protected ListStudentDTO convert(StudentEntity entity) {

                        ListStudentDTO createStudent = new ListStudentDTO();
                        createStudent.setName(entity.getName());
                        createStudent.setCpf(entity.getCpf());
                        createStudent.setBirthday(entity.getBirthday());
                        createStudent.setGender(entity.getGender());
                        createStudent.setPhone(entity.getPhone());
                        createStudent.setBelt(entity.getBelts());
                        return createStudent;
                    }
                });
    }
}
