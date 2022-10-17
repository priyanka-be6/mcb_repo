package com.mcb.entity;



import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "marks")
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Marks implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "mark_id")
    private int markId;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "mark")
    private int mark;

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
