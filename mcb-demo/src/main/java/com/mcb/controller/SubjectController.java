package com.mcb.controller;

import com.mcb.entity.Subject;
import com.mcb.repository.SubjectRepository;
import com.mcb.service.SubjectService;
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
public class SubjectController {

    private static final Logger log = LoggerFactory.getLogger(SubjectController.class);


    @Autowired
    private final SubjectRepository subjectRepository;

    @Autowired
    private final SubjectService subjectService;


    public SubjectController(SubjectRepository subjectRepository, SubjectService subjectService) {
        this.subjectRepository = subjectRepository;
        this.subjectService = subjectService;
    }

    //Fetching all

    @GetMapping(path = "/subject")
    public ResponseEntity<List<Subject>> getAll() {

        try {
            List<Subject> subjectList = subjectService.getAllSubject();
            return new ResponseEntity<>(subjectList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Fetching by id

    @GetMapping(path = "/subject/{subjectId}")
    public ResponseEntity<Subject> subjectById(@PathVariable Integer subjectId) {

        Optional<Subject> subjectData = subjectService.findOne(subjectId);

        if (subjectData.isPresent()){
            return  new ResponseEntity<Subject>(subjectData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //creating

    @PostMapping(path = "/subject")
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject) {
        try {
           // log.info("I am in Post method" + subjectDTO);
            Subject result = subjectService.add(subject);
            return new ResponseEntity<Subject> (result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Updating

    @PutMapping(path = "/subject/{subjectId}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Integer subjectId, @RequestBody Subject subject) {

        Optional<Subject> subjectData = subjectService.findOne(subjectId);

        if (subjectData.isPresent()) {
            Subject result = subjectService.Update(subject);
            return new ResponseEntity<Subject>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Deleting

    @DeleteMapping(path = "/subject/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Integer subjectId) {

        try {
            subjectService.delete(subjectId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get markList
    @GetMapping(path = "/subject/mark/{subjectId}")
    public ResponseEntity<List<Object>> getMarkList(@PathVariable Integer subjectId) {
        try {
            List<Object> markList = subjectService.getMarkDetails(subjectId);
            return new ResponseEntity<>(markList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
