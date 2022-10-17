package com.mcb;

import com.mcb.entity.Marks;
import com.mcb.entity.Student;
import com.mcb.repository.StudentRepository;
import com.mcb.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentServiceUT {

    @Autowired
    private StudentService service;

    @MockBean
    private StudentRepository repository;


    //findaAll
    @Test
    public void getAllStudentTest(){

        List<Marks> marksList1 =List.of(new Marks(2, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));
        List<Marks> marksList2 =List.of(new Marks(4, Timestamp.from(Instant.parse("2022-09-15T15:10:15Z")),89));

        //List<Student> student =List.of(new Student(1,"Mcb","Nayam",marksList));
        when(repository.findAll()).thenReturn(Stream.of(new Student(2,"Mcb","Nayam",marksList1)
                        ,new Student(3,"Mcb","Nayam",marksList2))
                .collect(Collectors.toList()));

        assertEquals(2,service.getAllStudent().size());
    }


//findById

    @Test
    public void getByIdStudentTest() {

        int id = 1;
        List<Marks> marksList =List.of(new Marks(2, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));
        Optional<Student> studentOptional = Optional.of(new Student(1,"Mcb","Nayam"
                ,marksList));

        when(repository.findById(id)).thenReturn(studentOptional);
        assertEquals(studentOptional,service.findOne(id));

    }

    @Test
    public void getSaveStudentTest(){
        List<Marks> marksList =List.of(new Marks(2, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));
        Student studentDetails = new Student(1,"Mcb","Nayam",marksList);
        when(repository.save(studentDetails)).thenReturn(studentDetails);
        Assertions.assertEquals(studentDetails,service.add(studentDetails));
    }

    //update
    @Test
    public void updateStudentTest(){
        List<Marks> marksList =List.of(new Marks(2, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));
        Student studentDetails = new Student(1,"Mcb","Nayam",marksList);
        Optional<Student> studentOptional =Optional.of(new Student(1,"Mcb","Nayam",marksList));
        if (studentOptional.isPresent()){
            when(repository.save(studentDetails)).thenReturn(studentDetails);
            Assertions.assertEquals(studentDetails,service.Update(studentDetails));
        }

    }

//Delete

    @Test
    public void deleteStudent(){

        int id = 1;

        List<Marks> marksList =List.of(new Marks(2, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));
        Student studentDetails = new Student(1,"Mcb","Nayam",marksList);
        Optional<Student> studentOptional =Optional.of(new Student(1,"Mcb","Nayam",marksList));
        if (studentOptional.isPresent()){
            service.delete(id);
            verify(repository,times(1)).deleteById(id);
        }

    }


//mark by id

    @Test
    public void getMarkByStudentId(){

        int id = 1;

        int mark = 67;

        List<Marks> marksList =List.of(new Marks(2, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));
        Student studentDetails = new Student(1,"Mcb","Nayam",marksList);
        Optional<Student> studentOptional =Optional.of(new Student(1,"Mcb","Nayam",marksList));
        if (studentOptional.isPresent()){
            when(repository.findMarkByStudentId(id)).thenReturn(mark);
            assertEquals(mark,service.getMarkByStudentId(id));
        }
    }



}
