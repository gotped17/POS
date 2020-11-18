/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.BL;

import at.htlkaindorf.beans.Stunde;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Gottl
 */
public class Supplierplan {

    private String klasse = "";
    private Map<Integer, List<Stunde>> stundenplan = new HashMap<>();
    private List<String> tage = new ArrayList<>();
    private List<String> tageKurz = new ArrayList<>();

    public Supplierplan(Path path) {
        tageKurz.add("Mo");
        tageKurz.add("Di");
        tageKurz.add("Mi");
        tageKurz.add("Do");
        tageKurz.add("Fr");
        tage.add("Montag");
        tage.add("Dienstag");
        tage.add("Mittwoch");
        tage.add("Donnerstag");
        tage.add("Freitag");
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
            klasse = br.readLine();
            br.close();
            List<Stunde> timetableRaw = Files.lines(path)
                    .skip(1)
                    .map(Stunde::new)
                    .collect(Collectors.toList());

            List<Stunde> stunden = Files
                    .lines(path)
                    .skip(1)
                    .map(Stunde::new)
                    .collect(Collectors.toList());

            for (int i = 1; i <= 10; i++) {
                stundenplan.put(i, new ArrayList<Stunde>());
            }
            int dayIndex = 0;
            int lessonIndex = 1;
            for (Stunde stunde : stunden) {
                List<Stunde> help = stundenplan.get(lessonIndex);
                if (help == null) {
                    help = new ArrayList<>();
                }
                help.add(stunde);
                stundenplan.put(lessonIndex, help);
                dayIndex++;
                if (dayIndex == 5) {
                    dayIndex = 0;
                    lessonIndex++;
                }
            }
            System.out.println(stundenplan.toString());
        } catch (IOException ex) {
            Logger.getLogger(Supplierplan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addSupplierung(String tag, int stunde, String supFach, List<String> supLehrer) {
        Stunde supStunde = new Stunde(supFach, supLehrer, true);
        List<Stunde> changedLesson = stundenplan.get(stunde);
        changedLesson.set(tage.indexOf(tag), supStunde);
        stundenplan.put(stunde, changedLesson);
    }

    public Map<Integer, List<Stunde>> getStundenplan() {
        return stundenplan;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public Stunde getStunde(String tag, int stunde) {
        List<Stunde> faecher = stundenplan.get(stunde);
        Stunde lesson = faecher.get(tageKurz.indexOf(tag));
        return lesson != null ? lesson : new Stunde("missing");

    }
}
