/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.joda.time.DateTime;

/**
 *
 * @author zsolti
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ora")
public class Lesson {

    @XmlAttribute
    private int id;

    @XmlElement(name = "idopont")
    @XmlJavaTypeAdapter(DayAdapter.class)
    private DateTime time;

    @XmlElement(name = "tantargy")
    private Subject subject;

    @XmlElement(name = "tanar")
    private Teacher teacher;

    @XmlElement(name = "terem")
    private Room room;

    public Lesson() {
    }

    public Lesson(int id, DateTime time, Subject subject, Teacher teacher, Room room) {
        this.id = id;
        this.time = time;
        this.subject = subject;
        this.teacher = teacher;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
