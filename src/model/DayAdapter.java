/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author zsolti
 */
public class DayAdapter extends XmlAdapter<String, Days> {

    public Days unmarshal(String v) throws Exception {
        Days nap = null;
        switch (v) {
            case "hétfő":
                nap = Days.HÉTFŐ;
                break;
            case "kedd":
                nap = Days.HÉTFŐ;
                break;
            case "szerda":
                nap = Days.HÉTFŐ;
                break;
            case "csütörtök":
                nap = Days.HÉTFŐ;
                break;
            case "péntek":
                nap = Days.HÉTFŐ;
                break;
            case "szombat":
                nap = Days.HÉTFŐ;
                break;
            case "vasárnap":
                nap = Days.HÉTFŐ;
                break;
        }
        return nap;
    }

    @Override
    public String marshal(Days v) throws Exception {
        String nap = null;
        switch (v) {
            case HÉTFŐ:
                nap = "hétfő";
                break;
            case KEDD:
                nap = "kedd";
                break;
            case SZERDA:
                nap = "szerda";
                break;
            case CSÜTÖRTÖK:
                nap = "csütörtök";
                break;
            case PÉNTEK:
                nap = "péntek";
                break;
            case SZOMBAT:
                nap = "szombat";
                break;
            case VASÁRNAP:
                nap = "vasárnap";
                break;
        }
        return nap;
    }
}
