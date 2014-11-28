/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.apache.log4j.Logger;
import util.JAXBUtil;

/**
 *
 * @author zsolti
 */
public class TeacherAdapter extends XmlAdapter<String, Teacher>{

    @Override
    public Teacher unmarshal(String v) throws Exception {
        Teacher t = new Teacher();
        t.setId(Integer.parseInt(v));
        return t;
    }

    @Override
    public String marshal(Teacher v) throws Exception {
        return String.valueOf(v.getId());
    }
    
}
