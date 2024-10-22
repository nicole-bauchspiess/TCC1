package com.br.dojo360.mapper;

import com.br.dojo360.exam.ExamEntity;
import com.br.dojo360.exam.dto.CreateExam;
import com.br.dojo360.exam.dto.CreateStudentExam;
import com.br.dojo360.exam.studentexam.StudentExamEntity;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamMappingConfiguration {

    ModelMapper modelMapper;

    @PostConstruct
    public void doMapping() {

        modelMapper.createTypeMap(CreateExam.class, ExamEntity.class)
                .setConverter(new AbstractConverter<CreateExam, ExamEntity>() {
                    @Override
                    protected ExamEntity convert(CreateExam createExam) {
                        ExamEntity examEntity = new ExamEntity();
                        examEntity.setId(createExam.getUuid());
                        examEntity.setDate(createExam.getDate());

                        createExam.getStudentExamList().forEach(s -> {
                            StudentExamEntity studentExamEntity = modelMapper.map(s, StudentExamEntity.class);
                            examEntity.getStudents().add(studentExamEntity);
                        });
                        return examEntity;
                    }
                });

        modelMapper.createTypeMap(ExamEntity.class, CreateExam.class)
                .setConverter(new AbstractConverter<ExamEntity, CreateExam>() {
                    @Override
                    protected CreateExam convert(ExamEntity examEntity) {
                        CreateExam createExam = new CreateExam();
                        createExam.setUuid(examEntity.getId());
                        createExam.setDate(examEntity.getDate());

                        List<CreateStudentExam> createStudentExams = new ArrayList<>();
                        for (StudentExamEntity studentExamEntity : examEntity.getStudents()) {
                            CreateStudentExam createStudentExam = modelMapper.map(studentExamEntity, CreateStudentExam.class);
                            createStudentExams.add(createStudentExam);
                        }
                        createExam.setStudentExamList(createStudentExams);

                        return createExam;
                    }
                });

        // Mapeamento de CreateStudentExam -> StudentExamEntity
//        modelMapper.createTypeMap(CreateStudentExam.class, StudentExamEntity.class)
//                .setConverter(new AbstractConverter<CreateStudentExam, StudentExamEntity>() {
//                    @Override
//                    protected StudentExamEntity convert(CreateStudentExam createStudentExam) {
//                        StudentExamEntity studentExamEntity = new StudentExamEntity();
//                        studentExamEntity.setId(UUID.randomUUID());
//                        studentExamEntity.setNewBelt(createStudentExam.getNewBelt());
//                        studentExamEntity.setStatus(createStudentExam.getStatus());
//
//                        // Mapear StudentEntity pelo ID
//                        StudentEntity studentEntity = new StudentEntity(createStudentExam.getStudentId());
//                        studentExamEntity.setStudent(studentEntity);
//
//                        return studentExamEntity;
//                    }
//                });
//
//        // Mapeamento de StudentExamEntity -> CreateStudentExam
//        modelMapper.createTypeMap(StudentExamEntity.class, CreateStudentExam.class)
//                .setConverter(new AbstractConverter<StudentExamEntity, CreateStudentExam>() {
//                    @Override
//                    protected CreateStudentExam convert(StudentExamEntity studentExamEntity) {
//                        CreateStudentExam createStudentExam = new CreateStudentExam();
//                        createStudentExam.setStudentId(studentExamEntity.getStudent().getId());
//                        createStudentExam.setNewBelt(studentExamEntity.getNewBelt());
//                        createStudentExam.setStatus(studentExamEntity.getStatus());
//
//                        return createStudentExam;
//                    }
//                });

    }
}
