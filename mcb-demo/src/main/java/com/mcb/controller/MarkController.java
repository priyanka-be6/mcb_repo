package com.mcb.controller;

import com.mcb.entity.Marks;
import com.mcb.repository.MarkRepository;
import com.mcb.service.MarkService;
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
public class MarkController {

    private static final Logger log = LoggerFactory.getLogger(MarkController.class);

    @Autowired
    private final MarkRepository markRepository;

    @Autowired
    private final MarkService markService;

    public MarkController(MarkRepository markRepository, MarkService markService) {
        this.markRepository = markRepository;
        this.markService = markService;
    }

    //Fetching all

    @GetMapping(path = "/mark")
    public ResponseEntity<List<Marks>> getAll() {

        try {
            List<Marks> marksList = markService.getAllMark();
            return new ResponseEntity<>(marksList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Fetching  By id

    @GetMapping(path = "/mark/{markId}")
    public ResponseEntity<Marks> markDetailsById(@PathVariable Integer markId) {

        Optional<Marks> mark = markService.findOne(markId);

        if (mark.isPresent()){
            return  new ResponseEntity<Marks>(mark.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //creating

    @PostMapping(path = "/mark")
    public ResponseEntity<Marks> createMark(@RequestBody Marks marks) {
        try {
          //  log.info("I am in Post method" + markDTO);
            Marks result = markService.add(marks);
            return new ResponseEntity<Marks> (result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Updating

    @PutMapping(path = "/mark/{markId}")
    public ResponseEntity<Marks> updateMarkDetail(@PathVariable Integer markId, @RequestBody Marks marks) {

        Optional<Marks> markData = markService.findOne(markId);

        if (markData.isPresent()) {
            Marks result = markService.update(marks);
            return new ResponseEntity<Marks>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Deleting

    @DeleteMapping(path = "/mark/{markId}")
    public ResponseEntity<Void> deleteMark(@PathVariable Integer markId) {

        try {
            markService.delete(markId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
