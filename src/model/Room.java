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
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="terem")
public class Room {
    
    @XmlAttribute
    private int id;
    
    @XmlElement(name="labor")
    private boolean labor;
    
    @XmlElement(name="projektor")
    private boolean projektor;
    
    @XmlElement(name="ferohely")
    private int ferohely;

    public Room(int id, boolean labor, boolean projektor, int ferohely) {
        this.id = id;
        this.labor = labor;
        this.projektor = projektor;
        this.ferohely = ferohely;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLabor() {
        return labor;
    }

    public void setLabor(boolean labor) {
        this.labor = labor;
    }

    public boolean isProjektor() {
        return projektor;
    }

    public void setProjektor(boolean projektor) {
        this.projektor = projektor;
    }

    public int getFerohely() {
        return ferohely;
    }

    public void setFerohely(int ferohely) {
        this.ferohely = ferohely;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }        
    
}
