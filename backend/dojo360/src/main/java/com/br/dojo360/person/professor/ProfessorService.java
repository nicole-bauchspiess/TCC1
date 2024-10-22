package com.br.dojo360.person.professor;

import com.br.dojo360.person.CreatePerson;
import com.br.dojo360.person.professor.dto.CreateProfessor;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProfessorService {

    @Inject
    private ProfessorRepository professorRepository;

    @Inject
    private ModelMapper mapper;

    private List<ProfessorEntity> findAll() {
        return professorRepository.findAll();
    }

    public ProfessorEntity findById(UUID uuid) {
        if (Objects.isNull(uuid)) {
            return new ProfessorEntity();
        }
        Optional<ProfessorEntity> entityOptional = professorRepository.findById(uuid);
        if (entityOptional.isEmpty()) {
            throw new NoSuchElementException("Professor não encontrado");
        }
        return entityOptional.get();
    }

    public CreatePerson createOrUpdateProfessor(CreatePerson newProfessor) {
        var professorToSave = findById(newProfessor.uuid());
        professorToSave = mapper.map(newProfessor, ProfessorEntity.class);
        professorRepository.save(professorToSave);
        return mapper.map(professorToSave, CreatePerson.class);
    }

}
