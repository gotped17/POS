/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.xml;

import at.htlkaindorf.pojo.CurrentWeather;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Gottl
 */
public class XMLAccess {
    private String apiCallBase = "http://api.openweathermap.org/data/2.5/weather?q=placeholder&units=metric&appid=ee55aa9a5253efeddb7cd8bef9b508aa&mode=xml";
    
    private static XMLAccess instance;
    
    public static XMLAccess getInstance() throws JAXBException{
        if(XMLAccess.instance == null){
            XMLAccess.instance = new XMLAccess();
        }
        return XMLAccess.instance;
    }
    private XMLAccess() throws JAXBException{
    }
    public CurrentWeather searchForCity(String city) throws MalformedURLException, JAXBException{
        String apiCall = apiCallBase.replace("placeholder", city);
        URL callUrl = new URL(apiCall);
        
        return JAXB.unmarshal(callUrl, CurrentWeather.class);
    }
    
    
    public static void main(String[] args) {
        try {
            XMLAccess xmla = XMLAccess.getInstance();
            System.out.println(xmla.searchForCity("Frohnleiten").toString());
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
}
