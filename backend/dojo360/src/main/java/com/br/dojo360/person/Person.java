package com.br.dojo360.person;

import com.br.dojo360.address.Address;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Person {

    @Id
    private UUID id;
    private String name;
    private String cpf;
    private char gender;
    private String email;
    private String phone;
    private Date birthday;
    private Address address;


    public Person() {
        this.id = UUID.randomUUID();
    }
}
