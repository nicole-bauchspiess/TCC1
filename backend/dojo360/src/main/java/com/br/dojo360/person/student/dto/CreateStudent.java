package com.br.dojo360.person.student.dto;

import com.br.dojo360.belt.Belts;

import java.time.LocalDate;

public record CreateStudent(
        String name,
        String cpf,
        LocalDate birthday,
        char gender,
        String email,
        String phone,
        Belts belt,
        String nFCK,
        String nCBK,
        String status
) {
}
