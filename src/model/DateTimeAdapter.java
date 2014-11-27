/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.joda.time.DateTime;

/**
 *
 * @author zsolti
 */
public class DateTimeAdapter extends XmlAdapter<String,DateTime>{
    public DateTime unmarshal(String v) throws Exception {
		return new DateTime(v);
	}

	public String marshal(DateTime v) throws Exception {
		return v.toString();
	}
}
