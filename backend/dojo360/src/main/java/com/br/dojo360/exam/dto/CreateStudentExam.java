package com.br.dojo360.exam.dto;

import com.br.dojo360.belt.Belts;
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
public class CreateStudentExam {

    private UUID studentId;
    private String studentName;
    private LocalDate studentBirthDay;
    private Belts actualBelt;
    private Belts newBelt;
    private StudentExamStatus status;
}
