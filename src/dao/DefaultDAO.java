/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;
import util.Connection;
import util.Database;
import util.JAXBUtil;

/**
 * @author zsolti
 * @param <T>
 */
public class DefaultDAO<T extends Object> {

    private Database db;
    private Connection conn;
    private final Class<T> objectClass;

    public DefaultDAO(Class<T> objectClass) {
        this.objectClass = objectClass;
        db = Database.getInstance();
        conn = db.getConnection();
    }

    protected void closeConnection() {
        db.freeConnection(conn);
    }

    protected T getObjectByQuery(String query) throws JAXBException, IOException {
        return getObject(query(query).get(0));
    }

    protected ArrayList<T> getObjectsByQuery(String query) throws IOException, JAXBException {
        return getObjects(query(query));
    }

    protected ArrayList<String> query(String query) throws IOException {
        return conn.query(query);
    }

    protected T getObject(String xml) throws JAXBException {
        return JAXBUtil.fromXMLElement(objectClass, xml);
    }

    protected ArrayList<T> getObjects(ArrayList<String> xml) throws JAXBException {
        return JAXBUtil.fromXMLElement(objectClass, xml);
    }
}
