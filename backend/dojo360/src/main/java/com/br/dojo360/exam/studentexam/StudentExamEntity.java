package com.br.dojo360.exam.studentexam;

import com.br.dojo360.belt.Belts;
import com.br.dojo360.exam.ExamEntity;
import com.br.dojo360.exam.dto.StudentExamStatus;
import com.br.dojo360.person.student.StudentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "student_exam")
@Getter
@Setter
@AllArgsConstructor
public class StudentExamEntity {

    @Id
    private UUID id;
    @Enumerated(EnumType.STRING)
    private StudentExamStatus status;
    private String paymentStatus;

    @Enumerated(EnumType.STRING)
    private Belts newBelt;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;

    public StudentExamEntity() {
        this.id = UUID.randomUUID();
    }

}
