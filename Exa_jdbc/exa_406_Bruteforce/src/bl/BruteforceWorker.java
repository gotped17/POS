/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Gottl
 */
public class BruteforceWorker implements Callable<String> {
    private Person person;
    private List<Character> passwordChars = new ArrayList<>();

    public BruteforceWorker(Person person) {
        this.person = person;
        initializeList();
    }
    
    
    @Override
    public String call() throws Exception {
        System.out.format("Started cracking %s %ss password\n"
                        + "--------------------------------\n", person.getFirstname(), person.getLastname());
        String pwString;
        
        long startTime = System.nanoTime();
       
        for (Character char1 : passwordChars) {
            for (Character char2 : passwordChars) {
                for (Character char3 : passwordChars) {
                    for (Character char4 : passwordChars) {
                        for (Character char5 : passwordChars) {
                            pwString = String.format("%c%c%c%c%c", char1,char2,char3,char4,char5);
                            if(checkHash(pwString)){
                                long endTime = System.nanoTime();
                                double duration = (endTime - startTime) / 1000000000;
                                System.out.format("%s %ss password '%s' has been cracked in %.2fseconds\n"
                                                + "---------------------------------------------------\n",
                                                person.getFirstname(), person.getLastname(), pwString, duration);
                                return pwString;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Cracking failed for " + person.getFirstname() + " " + person.getLastname() + "\n-------------------------------");
        return null;
    }
    public void initializeList(){
        for(char i = 'a'; i<='z';i++){
            passwordChars.add( i);
        }
        for(char i = '0'; i<= '9'; i++){
            passwordChars.add(i);
        }
       
    }
    
    
    public boolean checkHash(String password) throws NoSuchAlgorithmException{
        String toHash = person.getFirstname() + person.getLastname() + password;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(toHash.getBytes());
        String hashStr = DatatypeConverter.printHexBinary(hash).toLowerCase();
        
        return hashStr.equals(person.getHash());
    }
}
