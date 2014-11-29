/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basex;

import static basex.RunQueries.query;
import dao.ConsultingHourDAO;
import dao.MarkDAO;
import dao.RoomDAO;
import dao.SchoolYearDAO;
import dao.TeacherDAO;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import model.Mark;
import model.Room;
import model.SchoolYear;
import model.Student;
import model.Teacher;
import model.Class;
import model.ConsultingHour;
import model.Lesson;
import model.RoomAdapter;
import org.apache.log4j.PropertyConfigurator;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.XQuery;
import org.basex.query.value.node.ANode;
import util.BaseXClient;
import util.JAXBUtil;

/**
 * This example shows how queries can be executed in an iterative manner.
 * Iterative evaluation will be slower, as more server requests are performed.
 * 
* This example requires a running database server instance. Documentation:
 * http://docs.basex.org/wiki/Clients
 * 
* @author BaseX Team 2005-14, BSD License
 */
public final class QueryExample {

    /**
     * Main method.
     *
     * @param args command-line arguments
     */
    public static void main(final String[] args) throws IOException, JAXBException {
        
        //configure log4j
        PropertyConfigurator.configure("src\\main\\resources\\log4j.properties");
        
        /*
        try {
            SchoolYear f = new SchoolYearDAO().find("2014/2015");
            System.out.println(f);
            
            Teacher t = new TeacherDAO().find(1);
            System.out.println(t);
            Class c=new model.Class();
            c.setId("9/D-14/15");
            t.setForm(c);
            JAXBUtil.toXML(t, System.out);
            
            System.out.println(JAXBUtil.fromXMLElement(Teacher.class, "<tanar id=\"1\">\n" +
"    <nev>Kiss László</nev>\n" +
"    <email>kiss.laszlo@gmail.com</email>\n" +
"    <osztaly id='9/D-14/15'></osztaly>\n" +
"    <fogadoorak>\n" +
"        <fogadoora>\n" +
"            <nap>hétfő</nap>\n" +
"            <idopont>08:00:00</idopont>\n" +
"        </fogadoora>\n" +
"        <fogadoora>\n" +
"            <nap>kedd</nap>\n" +
"            <idopont>08:00:00</idopont>\n" +
"        </fogadoora>\n" +
"    </fogadoorak>\n" +
"</tanar>"));
            
            Room r = new RoomDAO().find(201);
            System.out.println(r);
            
            ArrayList<Room> rooms = new RoomDAO().findAll();
            System.out.println(rooms.toString());
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }*/

// create session
        BaseXClient session = new BaseXClient("localhost", 1984, "admin", "admin");
/*
        ConsultingHourDAO chdao = new ConsultingHourDAO();
        for(ConsultingHour c : chdao.findAll()) {
            System.out.println(c);
        }
  */
        
        MarkDAO mdao= new MarkDAO();
        Mark m = mdao.find("1");
        System.out.println(m);
        mdao.setObject(m);
        m.setId("2");
        new MarkDAO(m).add("2");
        m=new MarkDAO().find("2");
        System.out.println(m);
// create query instance
        String input = "for $x in doc('rendszer')/rendszer/osztalyok/osztaly return $x";

        BaseXClient.Query query = session.query(input);
       // while (query.more()) 
        {

            String xml=query.next();
            
            System.out.println(xml);
            Class c = JAXBUtil.fromXMLElement(Class.class, xml);
            System.out.println(c);
            JAXBUtil.toXML(c, System.out);
        }

        query.close();
    }
}
