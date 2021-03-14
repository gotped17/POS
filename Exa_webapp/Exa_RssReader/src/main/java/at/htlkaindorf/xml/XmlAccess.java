package at.htlkaindorf.xml;
import at.htlkaindorf.beans.Rss;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gottl
 */
public class XmlAccess {
    
    private static XmlAccess instance;
    
    public static XmlAccess getInstance() throws JAXBException{
        if(XmlAccess.instance == null){
            XmlAccess.instance = new XmlAccess();
        }
        return XmlAccess.instance;
    }
    
    public Rss getFeed(String linkToFeed) throws MalformedURLException{
        Rss rss = new Rss();
        // code to get rss feed
        rss = JAXB.unmarshal(new URL(linkToFeed), Rss.class);
        
        return rss;
    }
    
    public static void main(String[] args) {
        try {
            XmlAccess xmla = XmlAccess.getInstance();
            System.out.println(xmla.getFeed("https://rss.orf.at/science.xml"));
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
    
}
