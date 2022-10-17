package com.mcb.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "groups")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id")
    private int groupId;

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Student.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id",referencedColumnName = "group_id")
    private List<Student> student;

    @OneToMany(targetEntity = SubjectTeacher.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id",referencedColumnName = "group_id")
    private List<SubjectTeacher> subjectTeacher;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    public List<SubjectTeacher> getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(List<SubjectTeacher> subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                ", student=" + student +
                ", subjectTeacher=" + subjectTeacher +
                '}';
    }
}
