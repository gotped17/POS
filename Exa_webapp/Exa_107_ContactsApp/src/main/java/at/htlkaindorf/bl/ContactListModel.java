/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.bl;

import at.htlkaindorf.json.CompanyDeserializer;
import at.htlkaindorf.json.JSON_access;
import at.htlkaindorf.pojos.Company;
import at.htlkaindorf.pojos.Contact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;

/**
 *
 * @author TheCrether <thecrether.at>
 */
@Data
public class ContactListModel {

    private JSON_access json;
    private List<Contact> contacts = new ArrayList<>();
    private List<Contact> filteredContacts = new ArrayList<>();
    private Set<Contact> favouriteContacts = new HashSet<>();
    private String companyStr;
    private String gender;
    private String name;

    public ContactListModel(JSON_access json) {
        this.json = json;
        contacts.addAll(json.getContacts());
        filteredContacts.addAll(contacts);
    }

    public List<Contact> getContacts() {
        return filteredContacts;
    }

    public boolean contactExists(int id) {
        return filteredContacts.stream().map(Contact::getId).anyMatch((i) -> i == id);
    }

    public void removeContact(Contact contact) {
        if (contacts.contains(contact)) {
                contacts.remove(contact);
                filter(companyStr, name, gender);
        } else {
           // throw new IllegalArgumentException("Contact does not exist");
        }
    }

    public void addFavourite(int id) {
        if (contactExists(id)) {
            favouriteContacts.add(filteredContacts.get(id - 1));
        } else {
            throw new IllegalArgumentException("Contact does not exist");
        }
    }

    public String exportFavourites() throws JsonProcessingException {
        System.out.println(favouriteContacts);
        ObjectMapper om = new ObjectMapper();
        return om.writerWithDefaultPrettyPrinter().writeValueAsString(favouriteContacts);
    }

    public void filter(String companyStr, String nameStr, String gender) {
        String compName = "";
        String compMarket = "";
        Company company = null;
        if (companyStr.equals("select")) {
        } else {
            compName = companyStr.substring(0, companyStr.indexOf("(")).trim();
            compMarket = companyStr.substring(companyStr.indexOf("(") + 1, companyStr.length() - 1).trim();
            company = new Company(compName, compMarket);
        }

        final Company company_fin = company == null ? null : company;
        final String name = nameStr == null ? "" : nameStr;
        filteredContacts.clear();
        filteredContacts.addAll(
                contacts
                        .stream()
                        .filter((Contact c) -> {
                            boolean inQuestion = true;
                            if (company_fin != null) {
                                inQuestion = c.getCompany().equals(company_fin) && inQuestion;
                            }
                            inQuestion = c.getFullName().toLowerCase().contains(name.toLowerCase()) && inQuestion;

                            if (gender.toLowerCase().equals("male") || gender.toLowerCase().equals("female")) {
                                inQuestion = c.getGender().equalsIgnoreCase(gender) && inQuestion;
                            }
                            return inQuestion;
                        })
                        .collect(Collectors.toList())
        );
        this.companyStr = companyStr;
        this.gender = gender;
        this.name = nameStr;
    }

    public Set<String> getCompanyNames() {
        return CompanyDeserializer
                .getCompanies()
                .stream()
                .map(Company::getName)
                .collect(Collectors.toSet());
    }

    public Set<Company> getCompanies() {
        return CompanyDeserializer.getCompanies();
    }

}
