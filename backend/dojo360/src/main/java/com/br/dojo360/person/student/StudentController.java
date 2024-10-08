package com.br.dojo360.person.student;


import com.br.dojo360.person.student.dto.CreateStudent;
import com.br.dojo360.person.student.dto.ListStudent;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<List<ListStudent>> findAll() {
        List<StudentEntity> students = studentService.findAll();
        var newList = students
                .stream()
                .map(s -> mapper.map(s, ListStudent.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(newList);
    }

    @PostMapping
    public ResponseEntity<CreateStudent> createStudent(@RequestBody CreateStudent newStudent) {
        StudentEntity student = mapper.map(newStudent, StudentEntity.class);
        studentService.insert(student);
        var returnEntity = mapper.map(student, CreateStudent.class);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(student.getId()).toUri();
        return ResponseEntity.created(uri).body(returnEntity);
    }


}
