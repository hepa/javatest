/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basex;

import static basex.RunQueries.query;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import model.Mark;
import model.Student;
import model.Teacher;
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
        // print exception

// create session
        BaseXClient session = new BaseXClient("localhost", 1984, "admin", "admin");

// create query instance
        String input = "for $x in doc('rendszer')/rendszer/tanarok/tanar[@id='1'] return $x";
        BaseXClient.Query query = session.query(input);
        while (query.more()) {

            String xml = query.next();
            Teacher t = JAXBUtil.fromXMLElement(Teacher.class, xml);
            System.out.println(t.getForm());

        }

        query.close();
    }
}
