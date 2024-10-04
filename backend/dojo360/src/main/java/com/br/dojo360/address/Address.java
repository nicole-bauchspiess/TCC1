package com.br.dojo360.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "address")
@Table(name ="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "endereco_id")
    private Long id;
    private String logradouro;
    private String cep;
    private String bairro;
    private String localidade;
    private String uf;
    private String complemento;
    private String numero;


    public Address(AddressData data){
        this.cep = data.cep();
        this.numero = data.numero();
        this.logradouro = data.logradouro();
        this.bairro = data.bairro();
        this.localidade = data.localidade();
        this.uf = data.uf();
        this.complemento = data.complemento();
    }

    public void updateAddress(AddressData newAddress) {
        if(newAddress.logradouro()!= null) {
            this.logradouro = newAddress.logradouro();
        }
        if(newAddress.cep()!= null) {
            this.cep = newAddress.cep();
        }
        if(newAddress.bairro()!= null) {
            this.bairro = newAddress.bairro();
        }
        if(newAddress.localidade()!= null) {
            this.localidade = newAddress.localidade();
        }
        if(newAddress.uf()!= null) {
            this.uf = newAddress.uf();
        }
        if(newAddress.complemento()!= null) {
            this.complemento = newAddress.complemento();
        }
        if(newAddress.numero()!= null) {
            this.numero = newAddress.numero();
        }
    }
}
