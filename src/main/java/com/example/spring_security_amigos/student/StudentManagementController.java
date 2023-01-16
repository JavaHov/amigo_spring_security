package com.example.spring_security_amigos.student;


import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private final static List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Ola"),
            new Student(2, "Eva Maria"),
            new Student(3, "Tomas"),
            new Student(4, "Jaspis"));


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ADMIN_TRAINEE')")
    public List<Student> getAllStudents() {
        System.out.println("getAllStudents");
        return STUDENTS;
    }


    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println("registerNewStudent" + student);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("id") Integer  id) {
        System.out.println("deleteStudent" + id);
    }


    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("id") Integer id, @RequestBody Student student) {
        System.out.println("updateStudent" + id + ", student: " + student);
    }
}
