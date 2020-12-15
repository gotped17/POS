/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.json;

import at.htlkaindorf.pojos.Company;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;



public class CompanyDeserializer extends StdDeserializer<Company> {
    private static Set<Company> companies = new HashSet<>();
    
    public CompanyDeserializer(){
        super(Company.class);
    }
    
    @Override
    public Company deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        Company newCompany = jp.readValueAs(Company.class);
        companies.add(newCompany);
        return companies.stream().filter(c -> c.equals(newCompany)).findFirst().get();
    }
    
    public static Set<Company> getCompanies(){
        return companies;
    }
}
