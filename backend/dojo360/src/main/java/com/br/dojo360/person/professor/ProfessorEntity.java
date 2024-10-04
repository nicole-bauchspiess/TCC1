package com.br.dojo360.person.professor;

import com.br.dojo360.person.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "professor")
@Table(name ="professor")
public class ProfessorEntity extends Person {
}
