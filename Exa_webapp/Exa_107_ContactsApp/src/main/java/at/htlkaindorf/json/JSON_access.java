/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.json;

import at.htlkaindorf.pojos.Company;
import at.htlkaindorf.pojos.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.Data;

/**
 *
 * @author Gottl
 */
@Data
public class JSON_access {
    
    private List<Contact> contacts = new ArrayList<>();
    private Set<Company> companies = new HashSet<>();
    private boolean dataread = false;
    
    public JSON_access(Path path){
        ObjectMapper mapper = new ObjectMapper();
        Contact[] contactArr = new Contact[0];
        
        try {
            contactArr = mapper.readValue(path.toFile(), Contact[].class);
        } catch (IOException ex) {
            Logger.getLogger(JSON_access.class.getName()).log(Level.SEVERE, null, ex);
        }
        contacts = Arrays.asList(contactArr);
        companies = CompanyDeserializer.getCompanies();
        
        for(final Company c : companies){
            c.setContacts(contacts
                                .stream()
                                .filter(contact -> contact.getCompany().equals(c))
                                .collect(Collectors.toList()));
        }
    }
}
