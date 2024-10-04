package com.br.dojo360.person.professor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    private List<ProfessorEntity> findAll(){
        return professorRepository.findAll();
    }

    private ProfessorEntity findById(UUID uuid) {
        Optional<ProfessorEntity> entityOptional = professorRepository.findById(uuid);
        if (entityOptional.isEmpty()) {
            throw new NoSuchElementException("Professor não encontrado");
        }
        return entityOptional.get();
    }
}
