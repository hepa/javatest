/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;
import model.Subject;
import org.apache.commons.lang3.NotImplementedException;

/**
 *
 * @author zsolti
 */
public class SubjectDAO extends DefaultDAO<Subject> {

    private Subject subject;

    public SubjectDAO() {
        super(Subject.class);
        subject = null;
    }

    public SubjectDAO(Subject subject) {
        super(Subject.class);
        this.subject = subject;
    }

    public Subject find(String id) throws JAXBException, IOException {
        try {
            return getObjectByQuery("doc('rendszer')/rendszer/tantargyak/tantargy[@id='" + id + "']");
        } finally {
            closeConnection();
        }
    }
    
    public ArrayList<Subject> findAll() throws JAXBException, IOException {
        try {
            return getObjectsByQuery("doc('rendszer')/rendszer/tantargyak/tantargy");
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
