package com.br.dojo360.person.student;


import com.br.dojo360.person.student.dto.ListStudent;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ListStudent>> findAll(){
        List<StudentEntity> students = studentService.findAll();
        var newList = students
                .stream()
                .map(s-> mapper.map(s, ListStudent.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(newList);
    }
}
