package com.br.dojo360.exam.studentexam;

import com.br.dojo360.belt.Belts;
import com.br.dojo360.exam.ExamEntity;
import com.br.dojo360.person.student.StudentEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class StudentExamEntity {

    @Id
    private UUID id;
    private String status;
    private String paymentStatus;

    @Enumerated(EnumType.STRING)
    private Belts newBelt;

    @ManyToOne
    @JoinColumn(name="student_id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name="exam_id")
    private ExamEntity exam;

}
