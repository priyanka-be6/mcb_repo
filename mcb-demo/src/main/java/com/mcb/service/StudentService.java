package com.mcb.service;

import com.mcb.entity.Student;
import com.mcb.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private  StudentRepository studentRepository;



    public List<Student> getAllStudent() {
        List<Student> studentList = new ArrayList<Student>();
        studentRepository.findAll().forEach(Student ->studentList.add(Student));
        return studentList;
    }


    public Optional<Student> findOne(Integer studentId) {
        return studentRepository.findById(studentId);
    }


    public Student add(Student student){
        Student students= studentRepository.save(student);
        return students;
    }

    //Update

    public Student Update(Student student){
        Student students = studentRepository.save(student);
        return students;
    }

    //Delete by id

    public void delete(Integer studentId) {
        studentRepository.deleteById(studentId);
    }


    //mark by student id

    public int getMarkByStudentId(Integer studentId) {
        int mark = studentRepository.findMarkByStudentId(studentId);
        return mark;
    }



}
