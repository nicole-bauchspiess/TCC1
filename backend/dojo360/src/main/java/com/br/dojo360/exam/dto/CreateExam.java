package com.br.dojo360.exam.dto;

import com.br.dojo360.exam.dto.CreateStudentExam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CreateExam {

    private UUID uuid;
    private LocalDateTime date;
    private UUID professorId;
    private List<CreateStudentExam> studentExamList;

    public CreateExam () {
        this.studentExamList = new ArrayList<>();
    }
}