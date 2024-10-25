package com.br.dojo360.exam;

import com.br.dojo360.belt.Belts;
import com.br.dojo360.exam.dto.CreateExam;
import com.br.dojo360.exam.dto.CreateStudentExam;
import com.br.dojo360.exam.dto.StudentExamStatus;
import com.br.dojo360.exam.studentexam.StudentExamEntity;
import com.br.dojo360.exam.studentexam.StudentExamRepository;
import com.br.dojo360.person.professor.ProfessorEntity;
import com.br.dojo360.person.professor.ProfessorService;
import com.br.dojo360.person.student.StudentEntity;
import com.br.dojo360.person.student.StudentService;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ExamService {

    @Inject
    private ExamRepository examRepository;

    @Inject
    private ProfessorService professorService;

    @Inject
    private StudentService studentService;

    @Inject
    private ModelMapper mapper;

    @Inject
    private StudentExamRepository studentExamRepository;

    @Transactional
    public CreateExam createOrUpdateExam(CreateExam newExam) {
        ExamEntity exam;
        if (newExam.getUuid() == null) {
            exam = new ExamEntity();
        } else {
            exam = findById(newExam.getUuid());
            cleanStudentExam(exam);
        }
        exam.setDate(newExam.getDate());

        var professor = findProfessorById(newExam.getProfessorId());
        exam.setProfessor(professor);

        newExam.getStudentExamList().forEach(s -> {
            getStudentsExam(s, exam);
        });

        examRepository.save(exam);
        return mapper.map(exam, CreateExam.class);
    }

    private void cleanStudentExam(ExamEntity exam) {
        exam.getStudents().forEach(s -> studentExamRepository.delete(s));
        exam.getStudents().clear();
    }


    private void getStudentsExam(CreateStudentExam newStudentExam, ExamEntity exam) {
        var studentEntity = findStudentById(newStudentExam.getStudentId());
        StudentExamEntity studentExamEntity = new StudentExamEntity();
        studentExamEntity.setStudent(studentEntity);
        studentExamEntity.setExam(exam);
        studentExamEntity.setStatus(StudentExamStatus.NOT_INFORMED);
        studentExamEntity.setNewBelt(getNextBelt(studentEntity, newStudentExam));
        exam.getStudents().add(studentExamEntity);
    }

    public Belts getNextBelt(StudentEntity student, CreateStudentExam newStudentExam) {
        Belts newBelt;

        var actualBelt = student.getBelts();
        var initialBelts = student.getBelts().equals(Belts.BRANCA) || student.getBelts().equals(Belts.AZUL_CLARA) || student.getBelts().equals(Belts.AZUL_ESCURA);
        if (initialBelts) {
            if (newStudentExam.getNewBelt() == null) {
                throw new IllegalArgumentException("Informe uma faixa para alunos com faixas iniciais.");
            }
            newBelt = newStudentExam.getNewBelt();
        } else {
            if (student.getBelts().equals(Belts.PRETA)) {
                throw new IllegalArgumentException("Não é possível mudar de faixa.");
            }
            newBelt = Belts.values()[actualBelt.ordinal() + 1];
        }
        return newBelt;
    }

    public ProfessorEntity findProfessorById(UUID profId) {
        return professorService.findById(profId);
    }

    public StudentEntity findStudentById(UUID studentId) {
        return studentService.findById(studentId);
    }

    public ExamEntity findById(UUID uuid) {
        Optional<ExamEntity> entityOptional = examRepository.findById(uuid);
        if (entityOptional.isEmpty()) {
            throw new NoSuchElementException("Exame não encontrado.");
        }
        return entityOptional.get();
    }

}
