/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tests;

import util.BaseXClient;
import java.io.IOException;
import java.time.LocalTime;
import javax.xml.bind.JAXBException;
import model.Days;
import model.Teacher;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.JAXBUtil;

/**
 *
 * @author Rendszergazda
 */
public class Tanarok {
    
    private BaseXClient session;
    
    public Tanarok() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        session = new BaseXClient("localhost", 1984, "admin", "admin");
    }
    
    @After
    public void tearDown() throws IOException {
        session.close();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void tanarok() throws IOException, JAXBException {
        boolean volt = false;
        String input = "for $x in doc('rendszer')/rendszer/tanarok/tanar[@id='1'] return $x";        
        BaseXClient.Query query = session.query(input);        
        while (query.more()) {
            volt = true;
            String xml = query.next();            
            Teacher t = JAXBUtil.fromXMLElement(Teacher.class, xml);                        
            assertEquals("Kiss László", t.getName());
            assertEquals("kiss.laszlo@gmail.com", t.getEmail());
            assertEquals(t.getConsultingHours().size(), 2);
            assertEquals(t.getConsultingHours().get(0).getDay(), Days.HÉTFŐ);
            assertEquals(t.getConsultingHours().get(0).getHour(), LocalTime.parse("08:00:00"));
        }        
        assertEquals("Nem volt ilyen tanár. (Kiss László)", volt, true);
        
        query.close();
    }
}
