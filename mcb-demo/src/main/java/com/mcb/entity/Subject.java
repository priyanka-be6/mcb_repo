package com.mcb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "subjects")
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue//(strategy = GenerationType.AUTO)
    @Column(name = "subject_id")
    private int subjectId;

    @Column(name = "title")
    private String title;

    @OneToMany(targetEntity = Marks.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id",referencedColumnName = "subject_id")
    private List<Marks> marks;


    @OneToMany(targetEntity = SubjectTeacher.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
    private List<SubjectTeacher> subjectTeacher;


    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Marks> getMark() {
        return marks;
    }

    public void setMark(List<Marks> marks) {
        this.marks = marks;
    }

    public List<SubjectTeacher> getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(List<SubjectTeacher> subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", title='" + title + '\'' +
                ", mark=" + marks +
                ", subjectTeacher=" + subjectTeacher +
                '}';
    }
}
