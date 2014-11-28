/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;
import model.SchoolYear;
import org.apache.commons.lang3.NotImplementedException;

/**
 *
 * @author zsolti
 */
public class SchoolYearDAO extends DefaultDAO<SchoolYear> {

    private SchoolYear schoolYear;

    public SchoolYearDAO() {
        super(SchoolYear.class);
        schoolYear = null;
    }

    public SchoolYearDAO(SchoolYear schoolYear) {
        super(SchoolYear.class);
        this.schoolYear = schoolYear;
    }

    public SchoolYear find(String id) throws JAXBException, IOException {
        try {
            return getObjectByQuery("doc('rendszer')/rendszer/tanevek/tanev[@id='" + id + "']");
        } finally {
            closeConnection();
        }
    }

    public ArrayList<SchoolYear> findAll() throws JAXBException, IOException {
        try {
            return getObjectsByQuery("doc('rendszer')/rendszer/tanevek/tanev");
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
