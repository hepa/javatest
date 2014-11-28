/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;
import model.Room;
import org.apache.commons.lang3.NotImplementedException;

/**
 *
 * @author zsolti
 */
public class RoomDAO extends DefaultDAO<Room> {

    private Room room;

    public RoomDAO() {
        super(Room.class);
        room = null;
    }

    public RoomDAO(Room room) {
        super(Room.class);
        this.room = room;
    }

    public Room find(int id) throws JAXBException, IOException {
        try {
            return getObjectByQuery("doc('rendszer')/rendszer/termek/terem[@id='" + id + "']");
        } finally {
            closeConnection();
        }
    }
    
    public ArrayList<Room> findAll() throws JAXBException, IOException {
        try {
            return getObjectsByQuery("doc('rendszer')/rendszer/termek/terem");
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
