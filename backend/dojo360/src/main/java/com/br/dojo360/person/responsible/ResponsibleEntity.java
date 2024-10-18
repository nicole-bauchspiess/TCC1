package com.br.dojo360.person.responsible;

import com.br.dojo360.person.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity(name = "responsible")
@Table(name ="responsible")
public class ResponsibleEntity extends Person {

    public ResponsibleEntity(){
        this.setId(UUID.randomUUID());
    }

    public ResponsibleEntity(UUID id) {
        this.setId(id);
    }
}
