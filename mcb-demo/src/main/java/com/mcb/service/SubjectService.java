package com.mcb.service;



import com.mcb.entity.Subject;
import com.mcb.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private  SubjectRepository subjectRepository;




    public List<Subject> getAllSubject() {
        List<Subject> subjectList = new ArrayList<Subject>();
        subjectRepository.findAll().forEach(Subject ->subjectList.add(Subject));
        return subjectList;
    }


    public Optional<Subject> findOne(int subjectId) {
        return subjectRepository.findById(subjectId);
    }


    public Subject add(Subject subject){
        Subject subjectData = subjectRepository.save(subject);
        return subjectData;
    }

    //Update

    public Subject Update(Subject subject){
        Subject subjectData = subjectRepository.save(subject);
        return subjectData;
    }

    //Delete by id

    public void delete(Integer subjectId) {
        subjectRepository.deleteById(subjectId);
    }

    //List of Marks
    public List<Object> getMarkDetails(Integer subjectId) {
        List<Object> markList = subjectRepository.getMarksDetailsBySubjectId(subjectId);
        return markList;
    }
}
