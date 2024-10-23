package com.br.dojo360.mapper;

import com.br.dojo360.exam.ExamEntity;
import com.br.dojo360.exam.dto.CreateExam;
import com.br.dojo360.exam.dto.CreateStudentExam;
import com.br.dojo360.exam.studentexam.StudentExamEntity;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamMappingConfiguration {

    ModelMapper modelMapper;

    @PostConstruct
    public void doMapping() {


        modelMapper.createTypeMap(ExamEntity.class, CreateExam.class)
                .setConverter(new AbstractConverter<ExamEntity, CreateExam>() {
                    @Override
                    protected CreateExam convert(ExamEntity examEntity) {
                        CreateExam createExam = new CreateExam();
                        createExam.setUuid(examEntity.getId());
                        createExam.setDate(examEntity.getDate());
                        createExam.setStudentExamList(
                                examEntity.getStudents().stream().map(s -> modelMapper.map(s, CreateStudentExam.class)).collect(Collectors.toList())
                        );
                        return createExam;
                    }
                });


        modelMapper.createTypeMap(StudentExamEntity.class, CreateStudentExam.class)
                .setConverter(new AbstractConverter<StudentExamEntity, CreateStudentExam>() {
                    @Override
                    protected CreateStudentExam convert(StudentExamEntity studentExamEntity) {
                        CreateStudentExam createStudentExam = new CreateStudentExam();
                        var studentEntity = studentExamEntity.getStudent();
                        createStudentExam.setStudentId(studentEntity.getId());
                        createStudentExam.setStudentName(studentEntity.getName());
                        createStudentExam.setActualBelt(studentEntity.getBelts());
                        createStudentExam.setStudentBirthDay(studentEntity.getBirthday());
                        createStudentExam.setNewBelt(studentExamEntity.getNewBelt());
                        createStudentExam.setStatus(studentExamEntity.getStatus());

                        return createStudentExam;
                    }
                });

    }
}
