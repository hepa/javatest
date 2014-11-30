/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import util.BaseXClient;
import java.io.IOException;
import java.util.ArrayList;
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
    private Student s;

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
        String id = "1";        
        assertNull(s);
        s = Student.find(id);
        assertNotNull("No results found for id=" + id + ".", s);
        assertEquals("Reményi Szabolcs", s.getName());
        assertEquals("szabolcs.remenyi@gmail.com", s.getEmail());        
        
        s = null;
        id = "2";   
        assertNull(s);
        s = Student.find(id);
        assertNotNull("No results found for id=" + id + ".", s);
        assertEquals("Fehérvári Zsolt", s.getName());
        assertEquals("zsoltty91@hotmail.com", s.getEmail());         
        
        ArrayList<Student> students = new ArrayList<>();
        assertEquals(0, students.size());
        students = Student.findAll();
        assertEquals(2, students.size());        
    }
}
