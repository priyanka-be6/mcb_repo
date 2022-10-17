package com.mcb.service;

import com.mcb.entity.SubjectTeacher;
import com.mcb.repository.SubjectTeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectTeacherService {

    @Autowired
    private SubjectTeacherRepository teacherRepository;


    //Get all Teacher list

    public List<SubjectTeacher> getAllTeacher() {
        List<SubjectTeacher> teacherList = new ArrayList<SubjectTeacher>();
        teacherRepository.findAll().forEach(SubjectTeacher ->teacherList.add(SubjectTeacher));
        return teacherList;
    }


    //find by id

    public Optional<SubjectTeacher> findOne(int teacherId) {
        return teacherRepository.findById(teacherId);
    }

    //create

    public SubjectTeacher add(SubjectTeacher teacher){
        SubjectTeacher teacherData = teacherRepository.save(teacher);
        return teacherData;
    }

    //Update

    public SubjectTeacher Update(SubjectTeacher teacher){
        SubjectTeacher teacherData = teacherRepository.save(teacher);
        return teacherData;
    }

    //Delete by id

    public void delete(Integer teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    //count
    public int getCount(Integer teacherId) {
        int count = teacherRepository.countOfStudent(teacherId);
        return count;
    }
}
