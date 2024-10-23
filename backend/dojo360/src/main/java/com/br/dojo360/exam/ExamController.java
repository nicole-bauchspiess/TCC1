package com.br.dojo360.exam;

import com.br.dojo360.exam.dto.CreateExam;
import jakarta.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Inject
    private ExamService examService;

    @PostMapping
    public ResponseEntity<CreateExam> insertExam(@RequestBody CreateExam newExam) {
        var returnEntity = examService.insertExam(newExam);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(returnEntity).toUri();
        return ResponseEntity.created(uri).body(returnEntity);
    }
}
