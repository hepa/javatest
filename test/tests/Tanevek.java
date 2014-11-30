/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tests;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;
import model.SchoolYear;
import model.Student;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.BaseXClient;

/**
 *
 * @author Rendszergazda
 */
public class Tanevek {
    private BaseXClient session;
    private SchoolYear sy;
    
    public Tanevek() {
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
    public void test() throws JAXBException, IOException {
        String id = "2014/2015";
        assertNull(sy);
        sy = SchoolYear.find(id);
        assertNotNull("No results found for id=" + id + ".", sy);
        assertEquals(LocalDate.of(2014, 9, 1), sy.getFrom());
        assertEquals(LocalDate.of(2015, 6, 30), sy.getTo());
        assertEquals(4, sy.getHolidays().size());
        
        ArrayList<SchoolYear> schoolYears = SchoolYear.findAll();
        assertEquals(2, schoolYears.size());
    }
}
