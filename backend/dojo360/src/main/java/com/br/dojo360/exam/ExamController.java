package com.br.dojo360.exam;

import com.br.dojo360.exam.dto.CreateExam;
import jakarta.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/exam")
public class ExamController {

    @Inject
    private ExamService examService;

    @PostMapping
    public ResponseEntity<CreateExam> insertExam(CreateExam newExam) {
        return  null;
    }
}
