package com.br.dojo360.person.student;

import com.br.dojo360.person.student.dto.CreateStudent;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
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
        if (Objects.isNull(uuid)) {
            return new StudentEntity();
        }
        Optional<StudentEntity> entityOptional = studentRepository.findById(uuid);
        if (entityOptional.isEmpty()) {
            throw new NoSuchElementException("Aluno não encontrado.");
        }
        return entityOptional.get();
    }

    public CreateStudent createOrUpdateStudent(CreateStudent newStudent) {
        var studentToSave = findById(newStudent.getUuid());

        if (Objects.isNull(newStudent.getUuid())) {
            validateAge(newStudent);
            validateCpf(newStudent.getCpf());
            validateDate(newStudent.getBirthday());
        }

        studentToSave = mapper.map(newStudent, StudentEntity.class);
        studentRepository.save(studentToSave);

        return mapper.map(studentToSave, CreateStudent.class);
    }

    private void validateAge(CreateStudent newStudent) {
        var age = Period.between(newStudent.getBirthday(), LocalDate.now()).getYears();
        if (age < 18 && Objects.isNull(newStudent.getResponsible())) {
            throw new IllegalArgumentException("Aluno menor de idade. Informe um responsável.");
        } else if (Objects.isNull(newStudent.getResponsible()) && Objects.isNull(newStudent.getAddress())) {
            throw new IllegalArgumentException("Informe um endereço ou responsávvel.");
        }
    }

    private void validateCpf(String cpf) {
        var studentOptional = studentRepository.findByCpf(cpf);
        if (studentOptional.isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }
    }

    private void validateDate(LocalDate date) {
        if (date.isAfter(LocalDate.now()) || date.isBefore(LocalDate.of(1920, 1, 1))) {
            throw new IllegalArgumentException("Data de nascimento inválida.");
        }
    }
}
