package com.mcb.repository;

import com.mcb.entity.SubjectTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectTeacherRepository extends JpaRepository<SubjectTeacher, Integer> {

   // @Query("select count(s.studentId) from  SubjectTeacher t,Group g,Student s where t.teacherId = g.groupId and g.groupId = s.studentId")
   // @Query("select count(s.studentId),t.teacherId from  SubjectTeacher t,Group g,Student s where t.teacherId = g.teacherId and g.groupId = s.studentId")
   @Query("select count(s.studentId) from Group g join g.subjectTeacher t join g.student s WHERE t.teacherId = :teacherId")
   int countOfStudent(Integer teacherId);
}
