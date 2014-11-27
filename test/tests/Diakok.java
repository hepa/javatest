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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.JAXBUtil;

/**
 *
 * @author Rendszergazda
 */
public class Diakok {

    private BaseXClient session;

    public Diakok() {
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
    public void diakok() throws IOException, JAXBException {
        boolean volt = false;
        String input = "for $x in doc('rendszer')/rendszer/diakok/diak[@id='1'] return $x";
        BaseXClient.Query query = session.query(input);        
        while (query.more()) {
            volt = true;
            String xml = query.next();            
            Student s = JAXBUtil.fromXMLElement(Student.class, xml);            
            assertEquals(s.getName(), "Reményi Szabolcs");
            assertEquals(s.getEmail(), "szabolcs.remenyi@gmail.com");
        }        
        assertEquals(volt, true);
        
        volt = false;
        input = "for $x in doc('rendszer')/rendszer/diakok/diak[@id='2'] return $x";
        query = session.query(input);        
        while (query.more()) {
            volt = true;
            String xml = query.next();            
            Student s = JAXBUtil.fromXMLElement(Student.class, xml);            
            assertEquals(s.getName(), "Fehérvári Zsolt");
            assertEquals(s.getEmail(), "zsoltty91@hotmail.com");
        }        
        assertEquals(volt, true);
        query.close();
    }
}
