/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;
import model.Student;
import org.apache.commons.lang3.NotImplementedException;

/**
 *
 * @author zsolti
 */
public class StudentDAO extends DefaultDAO<Student>{
    private Student student;

    public StudentDAO() {
        super(Student.class);
        student=null;
    }
    
    public StudentDAO(Student student) {
        super(Student.class);
        this.student=student;
    }
    
    public Student find(int id) throws JAXBException, IOException {
        try {
            return getObjectByQuery("doc('rendszer')/rendszer/diakok/diak[@id='" + id + "']");
        } finally {
            closeConnection();
        }
    }
    
    public ArrayList<Student> findAll() throws JAXBException, IOException {
        try {
            return getObjectsByQuery("doc('rendszer')/rendszer/diakok/diak");
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
