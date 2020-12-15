/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.pojos;

import at.htlkaindorf.json.CompanyDeserializer;
import at.htlkaindorf.json.LocalDateDeserializer;
import at.htlkaindorf.json.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Gottl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @EqualsAndHashCode.Exclude
    private int id;
    private String firstname;
    private String lastname;
    @EqualsAndHashCode.Exclude
    private String[] email;
    private String gender;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @EqualsAndHashCode.Exclude
    private LocalDate dateOfBirth;
    @JsonDeserialize(using = CompanyDeserializer.class)
    @EqualsAndHashCode.Exclude
    private Company company;
    
    public String getDateFormatted(){
        return dateOfBirth.format(DTF);
    }
    
    public String getEmailString(){
        String result = "";
        for (String string : email) {
            result += string;
            if(!string.equals(email[email.length-1])){
                result += "<br>";
            }
        }
        return result;
    }
    public String getFullName(){
        return String.format("%s %s", firstname, lastname);
    }
}
