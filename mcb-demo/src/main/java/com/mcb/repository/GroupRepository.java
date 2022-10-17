package com.mcb.repository;

import com.mcb.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {

//    @Query("SELECT m.mark FROM Student s join s.marks m WHERE s.studentId = :studentId")

//    @Query("SELECT count()")

}
