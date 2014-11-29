/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;
import model.Holiday;
import org.apache.commons.lang3.NotImplementedException;

/**
 *
 * @author zsolti
 */
public class HolidayDAO extends DefaultDAO<Holiday> {

    public HolidayDAO() {
        super(Holiday.class);
    }

    public Holiday find(String id) throws JAXBException, IOException {
        try {
            return getObjectByQuery("doc('rendszer')//szunet[@id='" + id + "']");
        } finally {
            closeConnection();
        }
    }

    public ArrayList<Holiday> findAll() throws JAXBException, IOException {
        try {
            return getObjectsByQuery("doc('rendszer')//szunet");
        } finally {
            closeConnection();
        }
    }
    
    public void add() {
        throw new NotImplementedException("implementáld");
    }

    public void remove() {
        throw new NotImplementedException("implementáld");
    }
}
