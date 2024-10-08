package com.br.dojo360.person;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class Person {

    @Id
    private UUID id;
    private String name;
    private String cpf;
    private char gender;
    private String email;
    private String phone;
    private LocalDate birthday;
    //private Address address;
    private String status;


    public Person() {
        this.id = UUID.randomUUID();
    }
}
