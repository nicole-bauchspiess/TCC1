package com.br.dojo360.person.professor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CreateProfessor(
        UUID uuid,

        @NotBlank(message = "Nome do professor é obrigatório.")
        String name,

        @NotBlank(message = "CPF é obrigatório.")
        String cpf,

        @NotNull(message = "Gênero é obrigatório.")
        char gender,

        @Email(message = "Email inválido.")
        String email,

        String phone,

        @NotNull(message = "Data de nascimento é obrigatória.")
        LocalDate birthday,
        // Address address,
        String status
) {
}