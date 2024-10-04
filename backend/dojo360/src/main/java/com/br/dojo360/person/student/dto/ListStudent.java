package com.br.dojo360.person.student.dto;

import com.br.dojo360.belt.Belts;
import com.br.dojo360.person.student.StudentEntity;

import java.util.Date;

public record ListStudent (
        String name,
        String cpf,
        Date birthday,
        char gender,
        Belts belt,
        String phone
) {
    public ListStudent (StudentEntity student) {
        this (student.getName(), student.getCpf(), student.getBirthday(), student.getGender(),
                student.getBelts(), student.getPhone());
    }
}
