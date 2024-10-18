package com.br.dojo360.person.student.dto;

import com.br.dojo360.address.AddressData;
import com.br.dojo360.belt.Belts;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;

public record CreateStudent(
        UUID uuid,

        @NotBlank(message = "Nome do aluno é obrigatório.")
        String name,

        @NotBlank(message = "Documento é obrigatório.")
        String cpf,

        @NotBlank(message = "Data de nascimento é obrigatória.")
        LocalDate birthday,

        @NotBlank(message = "Gênero é obrigatório.")
        char gender,

        String email,
        String phone,

        @NotBlank(message = "Faixa é obrigatória.")
        Belts belt,

        int nFCK,
        int nCBK,
        String status,
        AddressData address
) {
}
