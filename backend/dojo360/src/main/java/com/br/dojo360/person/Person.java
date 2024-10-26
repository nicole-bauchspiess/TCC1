package com.br.dojo360.person;

import com.br.dojo360.address.AddressEntity;
import jakarta.persistence.*;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;
    private boolean isEnable;


    public Person() {
        this.id = UUID.randomUUID();
    }
}
