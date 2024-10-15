package com.br.dojo360.person.student.dto;

import com.br.dojo360.belt.Belts;
import com.br.dojo360.person.student.StudentEntity;

import java.time.LocalDate;

public record CreateStudent(
        String name,
        String cpf,
        LocalDate birthday,
        char gender,
        String email,
        String phone,
        Belts belt,
        int nFCK,
        int nCBK,
        String status
) {
}
