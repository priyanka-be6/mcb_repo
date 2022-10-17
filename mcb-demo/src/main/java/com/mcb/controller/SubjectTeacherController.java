package com.mcb.controller;

import com.mcb.entity.SubjectTeacher;
import com.mcb.repository.SubjectTeacherRepository;
import com.mcb.service.SubjectTeacherService;
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
public class SubjectTeacherController {

    private static final Logger log = LoggerFactory.getLogger(SubjectTeacherController.class);

    @Autowired
    private final SubjectTeacherRepository teacherRepository;

    @Autowired
    private final SubjectTeacherService teacherService;

    public SubjectTeacherController(SubjectTeacherRepository teacherRepository, SubjectTeacherService teacherService) {
        this.teacherRepository = teacherRepository;
        this.teacherService = teacherService;
    }

    @GetMapping(path = "/teacher")
    public ResponseEntity<List<SubjectTeacher>> getAll() {

        try {
            List<SubjectTeacher> teacherList = teacherService.getAllTeacher();
            return new ResponseEntity<>(teacherList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //creating a get mapping that retrieves the detail of a specific Group

    @GetMapping(path = "/teacher/{teacherId}")
    public ResponseEntity<SubjectTeacher> teacherById(@PathVariable Integer teacherId) {

        Optional<SubjectTeacher> teacherData = teacherService.findOne(teacherId);

        if (teacherData.isPresent()){
            return  new ResponseEntity<SubjectTeacher>(teacherData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //creating post mapping that post the Groups detail in the database

    @PostMapping(path = "/teacher")
    public ResponseEntity<SubjectTeacher> createTeacher(@RequestBody SubjectTeacher teacher) {
        try {
           // log.info("I am in Post method" + teacherDTO);
            SubjectTeacher result = teacherService.add(teacher);
            return new ResponseEntity<SubjectTeacher> (result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Updating a group details

    @PutMapping(path = "/teacher/{teacherId}")
    public ResponseEntity<SubjectTeacher> updateTeacher(@PathVariable Integer teacherId, @RequestBody SubjectTeacher teacher) {

        Optional<SubjectTeacher> teacherData = teacherService.findOne(teacherId);

        if (teacherData.isPresent()) {
            SubjectTeacher result = teacherService.Update(teacher);
            return new ResponseEntity<SubjectTeacher>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Deleting a groupDetails

    @DeleteMapping(path = "/teacher/{teacherId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Integer teacherId) {

        try {
            teacherService.delete(teacherId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //count
    @GetMapping(path = "/teacher/count/{teacherId}")
    public ResponseEntity<Integer> getStudentCount(@PathVariable Integer teacherId) {

        try {
            int count = teacherService.getCount(teacherId);
            return new ResponseEntity<>(count,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
