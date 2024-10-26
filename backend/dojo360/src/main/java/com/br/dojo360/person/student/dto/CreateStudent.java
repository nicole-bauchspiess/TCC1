package com.br.dojo360.person.student.dto;

import com.br.dojo360.address.AddressData;
import com.br.dojo360.belt.Belts;
import com.br.dojo360.person.CreatePerson;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudent {
    private UUID uuid;

    @NotBlank(message = "Nome do aluno é obrigatório.")
    private String name;

    @NotBlank(message = "Documento é obrigatório.")
    private String cpf;

    @NotBlank(message = "Data de nascimento é obrigatória.")
    private LocalDate birthday;

    @NotBlank(message = "Gênero é obrigatório.")
    private char gender;

    private String email;
    private String phone;

    @NotBlank(message = "Faixa é obrigatória.")
    private Belts belt;

    private int nFCK;
    private int nCBK;
    private boolean isEnable;
    private AddressData address;
    private CreatePerson responsible;

}