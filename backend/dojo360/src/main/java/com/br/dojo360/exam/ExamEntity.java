package com.br.dojo360.exam;

import com.br.dojo360.exam.studentexam.StudentExamEntity;
import com.br.dojo360.person.professor.ProfessorEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Entity(name = "exam")
@Table(name = "exam")
public class ExamEntity {

    @Id
    private UUID id;

    private LocalDateTime date;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<StudentExamEntity> students;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professor;

    public ExamEntity() {
        this.id = UUID.randomUUID();
        this.students = new ArrayList<>();
    }
}
