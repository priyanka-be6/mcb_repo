package com.mcb;

import com.mcb.entity.Marks;
import com.mcb.entity.Subject;
import com.mcb.entity.SubjectTeacher;
import com.mcb.repository.SubjectRepository;
import com.mcb.service.SubjectService;
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
public class SubjectServiceUT {

    @Autowired
    private SubjectService service;

    @MockBean
    private SubjectRepository repository;

    @Test
    public void getAllSubjectTest(){

        List<Marks> marksDetails = List.of(new Marks(2, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));
        List<SubjectTeacher> subjectTeacher =List.of(new SubjectTeacher(3));

        List<Marks> marksDetails2 = List.of(new Marks(2, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));
        List<SubjectTeacher> subjectTeacher2 =List.of(new SubjectTeacher(3));
       // List<Subject> subjectList = List.of(new Subject(1,"English",marksDetails,subjectTeacher));

        when(repository.findAll()).thenReturn(Stream.of(new Subject(1,"English",marksDetails,subjectTeacher)
                ,new Subject(1,"English",marksDetails2,subjectTeacher2))
                .collect(Collectors.toList()));
        assertEquals(2,service.getAllSubject().size());
    }

    //FindById
    @Test
    public void getSubjectByIdTest(){
        int id = 1;

        List<Marks> marksDetails = List.of(new Marks(2, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));
        List<SubjectTeacher> subjectTeacher =List.of(new SubjectTeacher(3));
        Optional<Subject> subjectOptional = Optional.of(new Subject(1,"English",marksDetails,subjectTeacher));
        when(repository.findById(id)).thenReturn(subjectOptional);
        assertEquals(subjectOptional,service.findOne(id));
    }

    //create
    @Test
    public void saveSubjectTest(){

        List<Marks> marksDetails = List.of(new Marks(2, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));
        List<SubjectTeacher> subjectTeacher =List.of(new SubjectTeacher(3));

        Subject subject = new Subject(1,"English",marksDetails,subjectTeacher);
        when(repository.save(subject)).thenReturn(subject);
        Assertions.assertEquals(subject,service.add(subject));
    }

    //delete

    @Test
    public void deleteSubjectByIdTest() {
        int id = 1;
        List<Marks> marksDetails = List.of(new Marks(2, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));
        List<SubjectTeacher> subjectTeacher =List.of(new SubjectTeacher(3));
        Optional<Subject> subjectOptional = Optional.of(new Subject(1,"English",marksDetails,subjectTeacher));
        if (subjectOptional.isPresent()) {
            service.delete(1);
            verify(repository, times(1)).deleteById(id);
        }
    }


}
