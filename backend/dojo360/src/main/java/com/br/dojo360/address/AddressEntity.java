package com.br.dojo360.address;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity(name = "address")
@Table(name = "address")
@AllArgsConstructor
public class AddressEntity {

    @Id
    private UUID id;
    private String street;
    private String cep;
    private String neighborhood;
    private String city;
    private String state;
    private String complement;
    private String number;


    public AddressEntity() {
        this.id = UUID.randomUUID();
    }

    public AddressEntity(UUID id) {
        this.id = id;
    }
}
