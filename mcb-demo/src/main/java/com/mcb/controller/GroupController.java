package com.mcb.controller;


import com.mcb.entity.Group;

import com.mcb.repository.GroupRepository;

import com.mcb.service.GroupsService;
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
public class GroupController {

    private static final Logger log = LoggerFactory.getLogger(GroupController.class);
    @Autowired
    private final GroupsService groupService;

    @Autowired
    private final GroupRepository groupRepository;

    public GroupController(GroupsService groupService, GroupRepository groupRepository) {
        this.groupService = groupService;
        this.groupRepository = groupRepository;
    }

    //creating a get mapping that retrieves all the groups detail from the database

    @GetMapping(path = "/group")
    public ResponseEntity<List<Group>> getAll() {

        try {
            List<Group> groupsList = groupService.getAllGroups();
            return new ResponseEntity<>(groupsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //creating a get mapping that retrieves the detail of a specific Group

    @GetMapping(path = "/group/{groupId}")
    public ResponseEntity<Group> groupById(@PathVariable Integer groupId) {

        Optional<Group> group = groupService.findOne(groupId);

        if (group.isPresent()){
            return  new ResponseEntity<Group>(group.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //creating post mapping that post the Groups detail in the database

    @PostMapping(path = "/group")
    public ResponseEntity<Group> createGroups(@RequestBody Group group) {
        try {
            //log.info("I am in Post method" + group);
            Group result = groupService.add(group);
            return new ResponseEntity<Group> (result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Updating a group details

    @PutMapping(path = "/group/{groupId}")
    public ResponseEntity<Group> updateGroups(@PathVariable Integer groupId, @RequestBody Group group) {

        Optional<Group> groupData = groupService.findOne(groupId);

        if (groupData.isPresent()) {
            Group result = groupService.update(group);
            return new ResponseEntity<Group>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Deleting a groupDetails

    @DeleteMapping(path = "/group/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Integer groupId) {

        try {
            groupService.delete(groupId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
