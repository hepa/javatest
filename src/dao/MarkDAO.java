/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;
import model.Mark;
import org.apache.commons.lang3.NotImplementedException;

/**
 *
 * @author zsolti
 */
public class MarkDAO extends DefaultDAO<Mark> {

    public MarkDAO() {
        super(Mark.class);
    }

    public Mark find(String id) throws JAXBException, IOException {
        try {
            return getObjectByQuery("doc('rendszer')/rendszer//jegy[@id='" + id + "']");
        } finally {
            closeConnection();
        }
    }

    public ArrayList<Mark> findAll() throws JAXBException, IOException {
        try {
            return getObjectsByQuery("doc('rendszer')/rendszer//jegy");
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
