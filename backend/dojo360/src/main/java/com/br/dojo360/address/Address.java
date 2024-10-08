package com.br.dojo360.address;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity(name = "address")
@Table(name = "address")
public class Address {

    @Id
    private UUID id;
    private String logradouro;
    private String cep;
    private String bairro;
    private String localidade;
    private String uf;
    private String complemento;
    private String numero;


    public Address() {
        this.id = UUID.randomUUID();
    }

}
