package com.mcb.repository;

import com.mcb.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query("SELECT s.title, m FROM Subject s JOIN s.marks m WHERE s.subjectId = :subjectId Group by s.title")
    List<Object> getMarksDetailsBySubjectId(Integer subjectId);
}
