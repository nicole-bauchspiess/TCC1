package com.br.dojo360.person.professor;

import com.br.dojo360.person.professor.dto.CreateProfessor;
import com.br.dojo360.person.student.dto.CreateStudent;
import jakarta.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Inject
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<ProfessorEntity> findALl() {

        return null;
    }

    @PostMapping
    public ResponseEntity<CreateProfessor> createStudent(@RequestBody CreateProfessor newProfessor) {

        var returnEntity = professorService.createOrUpdateProfessor(newProfessor);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(returnEntity).toUri();
        return ResponseEntity.created(uri).body(returnEntity);
    }
}
