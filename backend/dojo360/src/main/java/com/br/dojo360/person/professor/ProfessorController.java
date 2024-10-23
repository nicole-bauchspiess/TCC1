package com.br.dojo360.person.professor;

import com.br.dojo360.person.CreatePerson;
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
    public ResponseEntity<CreatePerson> createStudent(@RequestBody CreatePerson newProfessor) {

        var returnEntity = professorService.createOrUpdateProfessor(newProfessor);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(returnEntity).toUri();
        return ResponseEntity.created(uri).body(returnEntity);
    }
}
