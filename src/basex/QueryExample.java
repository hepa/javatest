/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basex;

import util.BaseXClient;
import static basex.RunQueries.query;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import model.Mark;
import model.Student;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.XQuery;
import org.basex.query.value.node.ANode;
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
    public static void main(final String[] args) {
        // print exception
    

    
        try {
// create session
            BaseXClient session = new BaseXClient("localhost", 1984, "admin", "admin");
        try {
// create query instance
            final String input = "for $x in doc('rendszer')/rendszer/diakok/diak return $x";
            final BaseXClient.Query query = session.query(input);
// loop through all results
            while (query.more()) {
                String xml = query.next();
System.out.println(xml);
                try {
                    Student s = JAXBUtil.fromXMLElement(Student.class, xml);
                    System.out.println(s);
                } catch (JAXBException ex) {
                    System.out.println(ex.getMessage());
                }
            }
String jegy = "<jegy id=\"1\">\n" +
"      <tantargy>K001</tantargy>\n" +
"      <tanev>2014/2015</tanev>\n" +
"      <erdemjegy>5</erdemjegy>\n" +
"    </jegy>";

                try {
                    Mark m = JAXBUtil.fromXMLElement(Mark.class, jegy);
                    System.out.println(m);
                } catch (JAXBException ex) {
                    System.out.println(ex.getMessage());
                }
            
            // print query info
            System.out.println(query.info());
// close query instance
            query.close();

          

        } catch (final IOException ex) {
// print exception
            ex.printStackTrace();
        }
// close session
        session.close();
    }
    catch (final IOException ex

    
        ) {
// print exception
            ex.printStackTrace();
    }
}
}
