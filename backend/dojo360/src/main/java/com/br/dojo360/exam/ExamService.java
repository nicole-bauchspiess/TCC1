package com.br.dojo360.exam;

import com.br.dojo360.exam.dto.CreateExam;
import com.br.dojo360.person.professor.ProfessorEntity;
import com.br.dojo360.person.professor.ProfessorService;
import com.br.dojo360.person.student.StudentEntity;
import com.br.dojo360.person.student.StudentService;
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExamService {

    @Inject
    private ExamRepository examRepository;

    @Inject
    private ProfessorService professorService;

    @Inject
    private StudentService studentService;

    public ExamEntity insertExam(CreateExam newExam) {
        ExamEntity exam = new ExamEntity();


        return exam;
    }

    public ProfessorEntity findProfessorById(UUID profId) {
        return professorService.findById(profId);
    }

    public StudentEntity findStudentById (UUID studentId) {
        return studentService.findById(studentId);
    }
}
