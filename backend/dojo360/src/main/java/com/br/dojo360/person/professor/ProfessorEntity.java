package com.br.dojo360.person.professor;

import com.br.dojo360.person.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity(name = "professor")
@Table(name ="professor")
public class ProfessorEntity extends Person {

    public ProfessorEntity(){
        this.setId(UUID.randomUUID());
    }
}
