package com.example.spring_security_amigos.student;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {


    private final static List<Student>  STUDENTS = Arrays.asList(
           new Student(1, "Ola"),
           new Student(2, "Eva Maria"),
            new Student(3, "Tomas"),
            new Student(4, "Jaspis")
    );
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") Integer id) {
        return STUDENTS.stream().filter(student -> id.equals(student.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("student not found"));
    }
}
