package com.br.dojo360.person.student;


import com.br.dojo360.person.student.dto.CreateStudentDTO;
import com.br.dojo360.person.student.dto.ListStudentDTO;
import com.br.dojo360.person.student.dto.StudentFilter;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Inject
    private StudentService studentService;

    @Inject
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ListStudentDTO>> findAll() {
        List<StudentEntity> students = studentService.findAll();
        var newList = students
                .stream()
                .map(s -> mapper.map(s, ListStudentDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(newList);
    }

    @PostMapping
    public ResponseEntity<CreateStudentDTO> createStudent(@RequestBody CreateStudentDTO newStudent) {

        var returnEntity = studentService.createOrUpdateStudent(newStudent);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(returnEntity).toUri();
        return ResponseEntity.created(uri).body(returnEntity);
    }

    @PostMapping
    public ResponseEntity<Page<ListStudentDTO>> findAllFilteredAndPaginated(@RequestBody StudentFilter filter) {
        Page<ListStudentDTO> dados = studentService.findStudentsPaginated(filter);
        return ResponseEntity.ok().body(dados);
    }
}
