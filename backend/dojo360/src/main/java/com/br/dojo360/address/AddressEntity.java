package com.br.dojo360.address;

import com.google.gson.annotations.SerializedName;
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
public class AddressEntity {

    @Id
    private UUID id;
    @SerializedName("logradouro")
    private String street;

    @SerializedName("cep")
    private String cep;

    @SerializedName("bairro")
    private String neighborhood;

    @SerializedName("localidade")
    private String city;

    @SerializedName("uf")
    private String state;

    private String complement;
    private String number;


    public AddressEntity() {
        this.id = UUID.randomUUID();
    }

}
