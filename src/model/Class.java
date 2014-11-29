/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 *
 * @author zsolti
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "osztaly")
public class Class implements XmlID{

    @XmlAttribute
    private String id;

    @XmlElement(name = "nev")
    private String name;

    @XmlElement(name = "tanev")
    private SchoolYear schoolYear;

    @XmlElement(name = "osztalyfonok")
    private Teacher teacher;

    @XmlElementWrapper(name = "diakok")
    @XmlElement(name = "diak")
    private ArrayList<Student> students;

    @XmlElementWrapper(name = "orarend")
    @XmlElement(name = "ora")
    ArrayList<Lesson> lessons;

    public Class() {
    }

    public Class(String id, String name, SchoolYear schoolYear, Teacher teacher, ArrayList<Student> students, ArrayList<Lesson> lessons) {
        this.id = id;
        this.name = name;
        this.schoolYear = schoolYear;
        this.teacher = teacher;
        this.students = students;
        this.lessons = lessons;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
