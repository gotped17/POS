/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.beans;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Gottl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD) // needed so jaxb accepts the  @XmlElement annotation
public class Student {
    @NonNull
    @XmlAttribute // sets this variable as an xml attribute
    private int catalognr;
    @NonNull
    //@XmlTransient // nimmt diese Variable von der xml datei aus
    private String firstname, lastname;
    @EqualsAndHashCode.Exclude
    @XmlElementWrapper(name="examList")
    @XmlElement(name = "exam") //to change the name of the tag
    private List<Exam> exams = new ArrayList<>();
    
    
    public void addExam(Exam exam){
        exams.add(exam);
    }
}
