/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import javax.xml.bind.JAXBException;
import model.SchoolYear;

/**
 *
 * @author zsolti
 */
public class SchoolYearDAO extends DefaultDAO<SchoolYear> {

    public SchoolYearDAO() {
        super(SchoolYear.class);
    }

    public SchoolYear find(String id) throws JAXBException, IOException {
        try {
            return getObjectByQuery("for $x in doc('rendszer')/rendszer/tanevek/tanev[id='" + id + "' return $x");
        } finally {
            closeConnection();
        }

    }
}
