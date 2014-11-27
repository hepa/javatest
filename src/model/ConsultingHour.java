/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
@XmlRootElement(name = "fogadoora")
public class ConsultingHour {

    @XmlElement(name = "nap")
    private Days day;

    @XmlElement(name = "idopont")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private DateTime hour;

    public ConsultingHour() {
    }

    public ConsultingHour(Days day, DateTime hour) {
        this.day = day;
        this.hour = hour;
    }

    public Days getDay() {
        return day;
    }

    public void setDay(Days day) {
        this.day = day;
    }

    public DateTime getHour() {
        return hour;
    }

    public void setHour(DateTime hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
