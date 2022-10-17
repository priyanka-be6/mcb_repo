package com.mcb;

import com.mcb.entity.Group;
import com.mcb.entity.Marks;
import com.mcb.entity.Student;
import com.mcb.entity.SubjectTeacher;
import com.mcb.repository.GroupRepository;
import com.mcb.service.GroupsService;
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
public class GroupServiceUT {

//    @Before
//    public void setUp() {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
//    }

    @Autowired
    private GroupsService service;

    @MockBean
    private GroupRepository repository;


    @Test
    public void getAllGroupTest() {
        List<Marks> marksList =List.of(new Marks(3, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));

        List<Student> student =List.of(new Student(2,"Mcb","Nayam",marksList));

        List<SubjectTeacher> subjectTeacher =List.of(new SubjectTeacher(4));

        when(repository.findAll()).thenReturn(Stream.of(new Group(1,"mcb",student,subjectTeacher)).collect(Collectors.toList()));
        assertEquals(1,service.getAllGroups().size());
    }

    //findById
    @Test
    public void getGroupByIdTest() {

        int id = 1;
//        Optional<Marks> marksList  = Optional.of(new Marks(3, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));
//        Optional<Student> student =Optional.of(new Student(2,"Mcb","Nayam",marksList));
        List<Marks> marksList = List.of(new Marks(3, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")), 67));

        List<Student> student = List.of(new Student(2, "Mcb", "Nayam", marksList));

        List<SubjectTeacher> subjectTeacher = List.of(new SubjectTeacher(4));

        Optional<Group> group = Optional.of(new Group(1,"mcb",student,subjectTeacher));
        when(repository.findById(id)).thenReturn(group);
        assertEquals(group,service.findOne(id));
    }

    //create

    @Test
    public void saveGroupTest() {

        List<Marks> marksList =List.of(new Marks(3, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));

        List<Student> student =List.of(new Student(2,"Mcb","Nayam",marksList));

        List<SubjectTeacher> subjectTeacher =List.of(new SubjectTeacher(4));

        Group group = new Group(1,"mcb",student,subjectTeacher);

        when(repository.save(group)).thenReturn(group);
        Assertions.assertEquals(group,service.add(group));

    }

    //update

    @Test
    public void updateGroupTests(){

        int id = 1;
        List<Marks> marksList =List.of(new Marks(3, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));

        List<Student> student =List.of(new Student(2,"Mcb","Nayam",marksList));

        List<SubjectTeacher> subjectTeacher =List.of(new SubjectTeacher(4));

        Group group = new Group(1,"mcb",student,subjectTeacher);
        Optional<Group> groupById = Optional.of(new Group(1,"mcb",student,subjectTeacher));
        if (groupById.isPresent()){
            when(repository.save(group)).thenReturn(group);
            Assertions.assertEquals(group,service.update(group));
        }

    }

    @Test
    public void deleteGroupTest() {
        int id = 1;
        List<Marks> marksList =List.of(new Marks(3, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));

        List<Student> student =List.of(new Student(2,"Mcb","Nayam",marksList));

        List<SubjectTeacher> subjectTeacher =List.of(new SubjectTeacher(4));

        Group group = new Group(1,"mcb",student,subjectTeacher);
        Optional<Group> groupById = Optional.of(new Group(1,"mcb",student,subjectTeacher));

        if (groupById.isPresent()){
            service.delete(id);
            verify(repository,times(1)).deleteById(id);
        }
    }

}
