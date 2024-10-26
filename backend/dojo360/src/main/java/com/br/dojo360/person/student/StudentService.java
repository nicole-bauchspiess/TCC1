package com.br.dojo360.person.student;

import com.br.dojo360.person.student.dto.CreateStudentDTO;
import com.br.dojo360.person.student.dto.ListStudentDTO;
import com.br.dojo360.person.student.dto.StudentFilter;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public CreateStudentDTO createOrUpdateStudent(CreateStudentDTO newStudent) {
        var studentToSave = findById(newStudent.getUuid());

        if (Objects.isNull(newStudent.getUuid())) {
            validateAge(newStudent);
            validateCpf(newStudent.getCpf());
            validateDate(newStudent.getBirthday());
        }

        studentToSave = mapper.map(newStudent, StudentEntity.class);
        studentRepository.save(studentToSave);

        return mapper.map(studentToSave, CreateStudentDTO.class);
    }

    public Page<ListStudentDTO> findStudentsPaginated (StudentFilter filter) {
        Integer page = 0;
        Integer size = 10;

        if (filter.getSize() != null && filter.getSize() > 0) {
            size = filter.getSize();
        }
        if (filter.getPage() != null && filter.getPage() >= 0) {
            page = filter.getPage();
        }

        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(filter.toSpec(), pageable).map(s-> mapper.map(s, ListStudentDTO.class));
    }

    private void validateAge(CreateStudentDTO newStudent) {
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
