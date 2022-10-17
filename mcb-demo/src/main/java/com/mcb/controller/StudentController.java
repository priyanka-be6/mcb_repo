package com.mcb.controller;

import com.mcb.entity.Student;
import com.mcb.repository.StudentRepository;
import com.mcb.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);


    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    private final StudentService studentService;

    public StudentController(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }


    @GetMapping(path = "/student")
    public ResponseEntity<List<Student>> getAll() {

        try {
            List<Student> studentList = studentService.getAllStudent();
            return new ResponseEntity<>(studentList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(path = "/student/{studentId}")
    public ResponseEntity<Student> studentById(@PathVariable Integer studentId) {

        Optional<Student> studentData = studentService.findOne(studentId);

        if (studentData.isPresent()){
            return  new ResponseEntity<Student>(studentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(path = "/student")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        try {

            log.info("I am in Post method" + student);
            Student result = studentService.add(student);
            return new ResponseEntity<Student> (result, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(path = "/student/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer studentId, @RequestBody Student student) {

        Optional<Student> studentData = studentService.findOne(studentId);

        if (studentData.isPresent()) {
            Student result =studentService.Update(student);
            return new ResponseEntity<Student> (result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/student/{studentId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Integer studentId) {

        try {
            studentService.delete(studentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //mark

    @GetMapping(path = "/student/mark/{studentId}")
    public ResponseEntity<Integer> getMark(@PathVariable Integer studentId) {
        try {
            int markData = studentService.getMarkByStudentId(studentId);
            return new ResponseEntity<>(markData,HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
