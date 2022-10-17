package com.mcb;

import com.mcb.entity.Marks;
import com.mcb.repository.MarkRepository;
import com.mcb.service.MarkService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MarkServiceUT {

    @MockBean
    private MarkRepository repository;

    @Autowired
    private MarkService service;

    @Test
    public void getAllMarkTest(){
        when(repository.findAll()).thenReturn(Stream.of(new Marks(1, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67)
        ,new Marks(2, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67))
                .collect(Collectors.toList()));
     assertEquals(2,service.getAllMark().size());
    }


    //findbyid

    @Test
    public void getMarksByIdTest(){
        int id = 1;

        Optional<Marks> marksById = Optional.of(new Marks(1, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));
        if (marksById.isPresent()){
            when(repository.findById(id)).thenReturn(marksById);
            assertEquals(marksById,service.findOne(id));
        }
    }

    //create
    @Test
    public void saveMarkTest(){
        Marks markDetals = new Marks(1, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67);
        when(repository.save(markDetals)).thenReturn(markDetals);
        Assertions.assertEquals(markDetals,service.add(markDetals));
    }

    //update

    @Test
    public void updateMarkTest(){
        int id = 1;
        Marks markDetals = new Marks(1, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67);
        Optional<Marks> marksById = Optional.of(new Marks(1, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));
        if (marksById.isPresent()){
            when(repository.save(markDetals)).thenReturn(markDetals);
            Assertions.assertEquals(markDetals,service.update(markDetals));
        }

    }

    //Delete
    @Test
    public void deleteMarksById(){
        int id = 1;
        Marks markDetals = new Marks(1, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67);
        Optional<Marks> marksById = Optional.of(new Marks(1, Timestamp.from(Instant.parse("2020-01-01T17:10:10Z")),67));
        if (marksById.isPresent()){
            service.delete(id);
            verify(repository,times(1)).deleteById(id);
        }
    }
}
