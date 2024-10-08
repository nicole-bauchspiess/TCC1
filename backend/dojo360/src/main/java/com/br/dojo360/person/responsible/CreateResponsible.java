package com.br.dojo360.person.responsible;

import java.time.LocalDate;

public record CreateResponsible(
        String name,
        String cpf,
        LocalDate birthday,
        char gender,
        String email,
        String phone) {
}
