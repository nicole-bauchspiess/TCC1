package com.br.dojo360.person.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository StudentRepository;

    public List<StudentEntity> findAll(){
        return StudentRepository.findAll();
    }

    public StudentEntity findById(UUID uuid) {
        Optional<StudentEntity> entityOptional = StudentRepository.findById(uuid);
        if (entityOptional.isEmpty()) {
            throw new NoSuchElementException("Student não encontrado");
        }
        return entityOptional.get();
    }
}
