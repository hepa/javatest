/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author zsolti
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="tanev")
public class SchoolYear {
    
    @XmlAttribute
    private String id;
    
    @XmlElement(name="mettol")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate from;
    
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement(name="meddig")
    private LocalDate to;
    
    @XmlElementWrapper(name="szunetek")
    @XmlElement(name="szunet")
    ArrayList<Holiday> holidays;

    public SchoolYear() {
        holidays=new ArrayList<>();
    }

    public SchoolYear(String id, LocalDate from, LocalDate to, ArrayList<Holiday> holidays) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.holidays = holidays;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public ArrayList<Holiday> getHolidays() {
        return holidays;
    }

    public void setHolidays(ArrayList<Holiday> holidays) {
        this.holidays = holidays;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
