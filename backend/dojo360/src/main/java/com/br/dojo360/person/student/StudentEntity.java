package com.br.dojo360.person.student;

import com.br.dojo360.address.AddressEntity;
import com.br.dojo360.belt.Belts;
import com.br.dojo360.exam.studentexam.StudentExamEntity;
import com.br.dojo360.person.Person;
import com.br.dojo360.person.responsible.ResponsibleEntity;
import com.br.dojo360.plan.PlanEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

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

    @OneToOne()
    @JoinColumn(name = "responsible_id")
    private ResponsibleEntity responsible;

    @ManyToOne()
    @JoinColumn(name = "plan_id")
    private PlanEntity plan;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<StudentExamEntity> exams;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    //private List<Classes> classes;
    public StudentEntity (UUID id) {
        this.setId(id);
    }

}
