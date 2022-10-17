package com.mcb.service;

import com.mcb.entity.Group;
import com.mcb.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class GroupsService {


    @Autowired
    private GroupRepository groupRepository;


    //getting all Groups records

    public List<Group> getAllGroups() {
        List<Group> groupsList = new ArrayList<Group>();
        groupRepository.findAll().forEach(Groups -> groupsList.add(Groups));
        return groupsList;
    }

    //getting a specific record

    public Optional<Group> findOne(int groupId) {
        return groupRepository.findById(groupId);
    }

    //saving a record into database
    public Group add(Group group){
       Group groups = groupRepository.save(group);
        return groups;
    }

    //updating a group into database

    public Group update(Group group) {
        Group groups = groupRepository.save(group);
        return groups;

    }

    //deleting a specific record

    public void delete(Integer groupId){

        groupRepository.deleteById(groupId);
    }

}
