package com.br.dojo360.person.professor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @GetMapping
    public ResponseEntity<ProfessorEntity> findALl() {

        return null;
    }
}
