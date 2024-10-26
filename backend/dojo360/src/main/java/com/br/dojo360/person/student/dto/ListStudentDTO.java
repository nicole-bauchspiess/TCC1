package com.br.dojo360.person.student.dto;

import com.br.dojo360.belt.Belts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListStudentDTO {

    private String name;
    private String cpf;
    private LocalDate birthday;
    private char gender;
    private Belts belt;
    private String phone;
}
