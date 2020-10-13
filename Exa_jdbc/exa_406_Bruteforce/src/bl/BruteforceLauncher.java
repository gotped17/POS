/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Gottl
 */
public class BruteforceLauncher {

    private List<Person> persons = new ArrayList<>();
    public void loadData() throws IOException{
        File Filepath = Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator + "res" + File.separator + "passwd_file.csv").toFile();
        BufferedReader br = new BufferedReader(new FileReader(Filepath));
        br.lines()
            .skip(1)
            .collect(Collectors.toList())
            .forEach(t -> {
                String[] tokens = t.split(",");
                persons.add(new Person(tokens[0],tokens[1],tokens[2],tokens[3]));
            });
    }
    
    public void crackPasswords(int anzPWs) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        List<BruteforceWorker> workers = new ArrayList<>();
        int cnt = 0;
        
        for (Person person : persons) {
            if(cnt<anzPWs){ 
                workers.add(new BruteforceWorker(person));
                cnt++;
            }
            else break;
        }
    
        List<Future<String>> futures = pool.invokeAll(workers);
        pool.shutdown();
        futures.forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException ex) {
                System.out.println(ex);
            }
        });
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean inputRight = false;
        int anzPws = 100;
        do{
            try{
                System.out.println("Enter how much passwords should be cracked (max. 100):");
                anzPws = Integer.parseInt(scan.next());
                inputRight = true;
            }catch(InputMismatchException | NumberFormatException ex){
                anzPws = 100;
            }
                
        }while(!inputRight);
        try {
            BruteforceLauncher bfLauncher = new BruteforceLauncher();
            bfLauncher.loadData();
            bfLauncher.crackPasswords(anzPws);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(BruteforceLauncher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
