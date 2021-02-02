/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.bl;

import at.htlkaindorf.beans.Exam;
import at.htlkaindorf.beans.Student;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Gottl
 */
public class JaxbTester {
    
    public static void main(String[] args) {
        Student student = new Student(1, "Daniel", "Brinar");
        student.addExam(new Exam((short) 1, "POS"));
        student.addExam(new Exam((short) 1, "Englisch"));
        //Complicated way - more possibilities
        try {
//            JAXBContext context = JAXBContext.newInstance(Student.class);
//            Marshaller marshaller = context.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // enables pretty print
//            marshaller.marshal(student, System.out); // marshalls the object to the given object, this case on the console
            
            
        //Simple way - simple use 
        JAXB.marshal(student, System.out);
         
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        
    }
    
}
