package com.br.dojo360.exam;

import com.br.dojo360.exam.dto.ExamDTO;
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
    public ResponseEntity<ExamDTO> insertExam(@RequestBody ExamDTO newExam) {
        var returnEntity = examService.createOrUpdateExam(newExam);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(returnEntity).toUri();
        return ResponseEntity.created(uri).body(returnEntity);
    }

    @PostMapping("/approveAll")
    public ResponseEntity<Void> approveAllStudents(@RequestBody ExamDTO examId) {
        examService.approveAllStudents(examId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/approveOrReprove")
    public ResponseEntity<Void> approveOrReproveSelected(@RequestBody ExamDTO exam) {
        examService.approveOrReproveSelected(exam);
        return ResponseEntity.noContent().build();
    }
}
