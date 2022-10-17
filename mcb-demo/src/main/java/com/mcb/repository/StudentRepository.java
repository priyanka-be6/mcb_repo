package com.mcb.repository;

import com.mcb.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

//    @Query("SELECT m.mark FROM Student s INNER JOIN Marks m ON s.studentId = m.markId")

    @Query("SELECT m.mark FROM Student s join s.marks m WHERE s.studentId = :studentId")
    Integer findMarkByStudentId(Integer studentId);
}
