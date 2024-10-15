package com.br.dojo360.person.student;

import com.br.dojo360.person.student.dto.CreateStudent;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
public class StudentService {

    @Inject
    private StudentRepository studentRepository;

    @Inject
    private ModelMapper mapper;


    public List<StudentEntity> findAll() {
        return studentRepository.findAll();
    }

    public StudentEntity findById(UUID uuid) {
        Optional<StudentEntity> entityOptional = studentRepository.findById(uuid);
        if (entityOptional.isEmpty()) {
            throw new NoSuchElementException("Student não encontrado");
        }
        return entityOptional.get();
    }

    public StudentEntity insert(StudentEntity student) {
        return studentRepository.save(student);
    }

    public CreateStudent generateStudent(CreateStudent newStudent) {
        StudentEntity student = mapper.map(newStudent, StudentEntity.class);
        insert(student);

        return mapper.map(student, CreateStudent.class);
    }

    private void validateAge(CreateStudent newStudent) {
        var age = Period.between(LocalDate.now(), newStudent.birthday()).getYears();
        if (age < 18) {
            throw new IllegalArgumentException("Aluno menor de idade. Informe um responsável");
        }
    }
}
