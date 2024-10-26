package com.br.dojo360.exam;

import com.br.dojo360.belt.Belts;
import com.br.dojo360.exam.dto.ExamDTO;
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
    public ExamDTO createOrUpdateExam(ExamDTO newExam) {
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
        return mapper.map(exam, ExamDTO.class);
    }

    public void approveAllStudents(ExamDTO exam) {
        var examEntity = findById(exam.getUuid());

        examEntity.getStudents().forEach(s-> s.setStatus(StudentExamStatus.APPROVED));
        examRepository.save(examEntity);
    }

    public void approveOrReproveSelected(ExamDTO createExam) {
        var examEntity = findById(createExam.getUuid());

        createExam.getStudentExamList().forEach(id -> {
            examEntity.getStudents().forEach(studentExam -> {
                if (studentExam.getId().equals(id.getStudentId())) {
                    studentExam.setStatus(id.getStatus());
                }
            });
        });
        examRepository.save(examEntity);
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

    private Belts getNextBelt(StudentEntity student, CreateStudentExam newStudentExam) {
        Belts newBelt;

        var actualBelt = student.getBelts();
        var initialBelts = student.getBelts().equals(Belts.BRANCA) || student.getBelts().equals(Belts.AZUL_CLARA);
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

    private ProfessorEntity findProfessorById(UUID profId) {
        return professorService.findById(profId);
    }

    private StudentEntity findStudentById(UUID studentId) {
        return studentService.findById(studentId);
    }

    private ExamEntity findById(UUID uuid) {
        Optional<ExamEntity> entityOptional = examRepository.findById(uuid);
        if (entityOptional.isEmpty()) {
            throw new NoSuchElementException("Exame não encontrado.");
        }
        return entityOptional.get();
    }

}
