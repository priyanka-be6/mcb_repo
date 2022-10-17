package com.mcb;

import com.mcb.entity.SubjectTeacher;
import com.mcb.repository.SubjectTeacherRepository;
import com.mcb.service.SubjectTeacherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SubjectTeacherUT {

    @Autowired
    private SubjectTeacherService teacherService;

    @MockBean
    private SubjectTeacherRepository repository;
   //read all
    @Test
    public void getAllTeacherTest() {
        when(repository.findAll()).thenReturn(Stream.of(new SubjectTeacher(4),new SubjectTeacher(2))
                .collect(Collectors.toList()));
        assertEquals(2,teacherService.getAllTeacher().size());
    }

    //read by id

    @Test
    public void getTeacherById(){
        int id = 1;
        Optional<SubjectTeacher> teacher = Optional.of(new SubjectTeacher(1));
        when((repository.findById(id))).thenReturn(teacher);
        assertEquals(teacher,teacherService.findOne(id));
    }

    //save teacher

    @Test
    public void saveTecherTest(){
        SubjectTeacher teacher = new SubjectTeacher(1);
        when(repository.save(teacher)).thenReturn(teacher);
        Assertions.assertEquals(teacher,teacherService.add(teacher));
    }

    //delete

    @Test
    public void deleteTecaherTest(){
        int id = 1;
        Optional<SubjectTeacher> teacher = Optional.of(new SubjectTeacher(1));
        if (teacher.isPresent()){
            teacherService.delete(id);
            verify(repository,times(1)).deleteById(id);
        }

    }
}
