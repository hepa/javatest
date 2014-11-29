/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;
import model.ConsultingHour;
import org.apache.commons.lang3.NotImplementedException;

/**
 *
 * @author zsolti
 */
public class ConsultingHourDAO extends DefaultDAO<ConsultingHour> {

    public ConsultingHourDAO() {
        super(ConsultingHour.class);
    }
    
    public ConsultingHour find(String id) throws JAXBException, IOException {
     try {
            return getObjectByQuery("doc('rendszer')//fogadoora[@id='" + id + "']");
        } finally {
            closeConnection();
        }
    }
    
    public ArrayList<ConsultingHour> findAll() throws JAXBException, IOException {
     try {
            return getObjectsByQuery("doc('rendszer')//fogadoora");
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
