package com.mcb.service;

import com.mcb.entity.Marks;
import com.mcb.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MarkService {

    @Autowired
    private MarkRepository markRepository;


    public List<Marks> getAllMark() {
        List<Marks> marksList = new ArrayList<Marks>();
        markRepository.findAll().forEach(Mark -> marksList.add(Mark));
        return marksList;
    }


    public Optional<Marks> findOne(int markId) {
        return markRepository.findById(markId);
    }


    public Marks add(Marks marks){

        Marks addMark = markRepository.save(marks);
        return addMark;
    }

    //Update

    public Marks update(Marks marks){
        Marks createMarks = markRepository.save(marks);
        return createMarks;
    }

    //Delete by id

    public void delete(Integer markId) {
        markRepository.deleteById(markId);
    }

}
