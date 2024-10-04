package com.br.dojo360.person.student;

import com.br.dojo360.belt.Belts;
import com.br.dojo360.exam.studentexam.StudentExamEntity;
import com.br.dojo360.person.Person;
import com.br.dojo360.person.responsible.ResponsibleEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "student")
@Table(name ="student")
public class StudentEntity extends Person {

    private int nFCK;
    private int nCBK;

    @Enumerated(EnumType.STRING)
    private Belts belts;

    private ResponsibleEntity responsible;
    //private Plan plan;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<StudentExamEntity> exams;

    //private List<Classes> classes;


}
