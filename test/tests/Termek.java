/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import util.BaseXClient;
import java.io.IOException;
import javax.xml.bind.JAXBException;
import model.Room;
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
public class Termek {

    private BaseXClient session;

    public Termek() {
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
    public void termek() throws IOException, JAXBException {
        boolean volt = false;
        String input = "for $x in doc('rendszer')/rendszer/termek/terem[@id='201'] return $x";
        BaseXClient.Query query = session.query(input);
        while (query.more()) {
            volt = true;
            String xml = query.next();
            Room r = JAXBUtil.fromXMLElement(Room.class, xml);
            assertEquals(r.isLabor(), true);
            assertEquals(r.isProjektor(), false);
            assertEquals(r.getFerohely(), 15);
        }
        assertEquals("Nem volt ilyen terem. (201)", volt, true);
        
        volt = false;
        input = "for $x in doc('rendszer')/rendszer/termek/terem[@id='202'] return $x";
        query = session.query(input);
        while (query.more()) {
            volt = true;
            String xml = query.next();
            Room r = JAXBUtil.fromXMLElement(Room.class, xml);
            assertEquals(r.isLabor(), true);
            assertEquals(r.isProjektor(), true);
            assertEquals(r.getFerohely(), 15);
        }
        assertEquals("Nem volt ilyen terem. (202)", volt, true);
        
        volt = false;
        input = "for $x in doc('rendszer')/rendszer/termek/terem[@id='105'] return $x";
        query = session.query(input);
        while (query.more()) {
            volt = true;
            String xml = query.next();
            Room r = JAXBUtil.fromXMLElement(Room.class, xml);
            assertEquals(r.isLabor(), false);
            assertEquals(r.isProjektor(), false);
            assertEquals(r.getFerohely(), 32);
        }
        assertEquals("Nem volt ilyen terem. (105)", volt, true);
    }
}
