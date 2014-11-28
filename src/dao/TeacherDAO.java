/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;
import model.Teacher;
import org.apache.commons.lang3.NotImplementedException;

/**
 *
 * @author zsolti
 */
public class TeacherDAO extends DefaultDAO<Teacher> {

    private Teacher teacher;
    
    public TeacherDAO() {
        super(Teacher.class);
        teacher=null;
    }
    
    public TeacherDAO(Teacher teacher) {
        super(Teacher.class);
        this.teacher=teacher;
    }

    public Teacher find(int id) throws JAXBException, IOException {
        try {
            return getObjectByQuery("doc('rendszer')/rendszer/tanarok/tanar[@id='" + id + "']");
        } finally {
            closeConnection();
        }
    }
    
    public ArrayList<Teacher> findAll() throws JAXBException, IOException {
        try {
            return getObjectsByQuery("doc('rendszer')/rendszer/tanarok/tanar");
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
