/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tests;

import basex.BaseXClient;
import java.io.IOException;
import javax.xml.bind.JAXBException;
import model.Student;
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
            assertEquals(t.getName(), "Kiss László");
            assertEquals(t.getEmail(), "kiss.laszlo@gmail.com");
            assertEquals(t.getClass(), "9/D-14/15");
        }        
        assertEquals("Nem volt ilyen tanár. (Kiss László)", volt, true);
        
        query.close();
    }
}
