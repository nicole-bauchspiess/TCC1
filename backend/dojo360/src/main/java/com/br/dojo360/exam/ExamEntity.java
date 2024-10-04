package com.br.dojo360.exam;

import com.br.dojo360.exam.studentexam.StudentExamEntity;
import com.br.dojo360.person.professor.ProfessorEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "exam")
@Table(name ="exam")
public class ExamEntity {

    @Id
    private UUID id;

    private LocalDateTime date;

    @OneToMany(mappedBy = "exam")
    private List<StudentExamEntity> students;
   // private ProfessorEntity professor;
}
