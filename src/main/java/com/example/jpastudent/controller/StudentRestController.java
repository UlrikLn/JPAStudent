package com.example.jpastudent.controller;

import com.example.jpastudent.model.Student;
import com.example.jpastudent.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class StudentRestController
{
    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> studentList()
    {
        return studentRepository.findAll();
    }

    @PostMapping("/student")
    @ResponseStatus(HttpStatus.CREATED)
    public Student postStudent(@RequestBody Student student)
    {
        System.out.println(student);
        return studentRepository.save(student);
    }

    @PutMapping("/student/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> putStudent(@PathVariable int id, @RequestBody Student student)
    {
        Optional<Student> orgStudent = studentRepository.findById(id);
        if (orgStudent.isPresent()){
            student.setId(id);
            studentRepository.save(student);
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id)
    {
        Optional<Student> orgStudent = studentRepository.findById(id);
        if (orgStudent.isPresent()){
            studentRepository.deleteById(id);
            return ResponseEntity.ok("Student with id=" + id + " deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with id=" + id + " not found");
        }
    }

    @GetMapping("/unicode/{i}")
    public String unicodeToChar(@PathVariable int i) {

        char c = (char)i;

        return "unicode=" + i + " char=" + c;

    }

    @GetMapping("/unicode2/{i}")
    public String charToUnicode(@PathVariable int i) {

        char c = (char)i;

        return "unicode=" + i + " char=" + c;

    }
}
