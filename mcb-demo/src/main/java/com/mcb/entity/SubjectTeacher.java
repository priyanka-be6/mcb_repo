package com.mcb.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "subject_teacher")
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SubjectTeacher implements Serializable {

    @Id
    @GeneratedValue//(strategy = GenerationType.AUTO)
    private int teacherId;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "SubjectTeacher{" +
                "teacherId=" + teacherId +
                '}';
    }
}
