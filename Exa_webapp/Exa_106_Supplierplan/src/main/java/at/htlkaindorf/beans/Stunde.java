/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Gottl
 */
public class Stunde {

    private String fach;
    private List<String> lehrer = new ArrayList<>();
    private boolean suppliert;

    public Stunde() {
    }

    public Stunde(String rawLesson) {
        String[] tokens = rawLesson.split(";");
        this.fach = tokens[0];
        String[] lehrer = tokens.length > 2 ? new String[tokens.length - 1] : tokens[1].split(",");
        if (tokens.length > 2) {
            for (int i = 1; i < tokens.length; i++) {
                lehrer[i - 1] = tokens[i];
            }
        }
        this.lehrer.addAll(Arrays.asList(lehrer));
        this.suppliert = false;
    }

    public Stunde(String fach, boolean suppliert) {
        this.fach = fach;
        this.suppliert = suppliert;
    }

    public String getFach() {
        return fach;
    }

    public void setFach(String fach) {
        this.fach = fach;
    }

    public List<String> getLehrer() {
        return lehrer;
    }

    public void setLehrer(List<String> lehrer) {
        this.lehrer = lehrer;
    }

    public boolean isSuppliert() {
        return suppliert;
    }

    public void setSuppliert(boolean suppliert) {
        this.suppliert = suppliert;
    }

}
