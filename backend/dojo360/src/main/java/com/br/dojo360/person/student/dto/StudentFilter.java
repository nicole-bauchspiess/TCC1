package com.br.dojo360.person.student.dto;

import com.br.dojo360.belt.Belts;

import java.time.LocalDate;
import java.util.List;

public class StudentFilter {

    private String name;
    private String cpf;
    private char gender;
    private String phone;
    private LocalDate birthday;
    private List<Belts> belt;
    private boolean isEnable;

}
