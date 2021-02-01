/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.xml;

import at.htlkaindorf.pojos.Window;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Gottl
 */
public class XMLIniApp {

    private Document doc;
    private List<Window> windows = new ArrayList<>();

    private static XMLIniApp instance;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;
    private static final int DEFAULT_X = 100;
    private static final int DEFAULT_Y = 100;
    Path path;

    public static XMLIniApp getInstance() throws ParserConfigurationException, SAXException {
        if (XMLIniApp.instance == null) {
            XMLIniApp.instance = new XMLIniApp();
        }
        return XMLIniApp.instance;
    }

    private XMLIniApp() throws ParserConfigurationException, SAXException {
        path = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "at", "htlkaindorf", "res", "windows.xml");
        loadIniFile();

    }

    //loads the xml file and initiates the doc element. If xml file is missing, the doc element is created programmatically
    public void loadIniFile() throws SAXException, ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        File file = path.toFile();
        try {
            doc = builder.parse(file);
            NodeList nodes = doc.getDocumentElement().getElementsByTagName("window");
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node instanceof Element) {
                    Window w = new Window((Element) node);
                    windows.add(w);
                }
            }
        } catch (IOException ex) {
            doc = builder.newDocument();
            doc.appendChild(doc.createElement("windows"));
        }
    }

    public void saveIniFile() throws TransformerConfigurationException, FileNotFoundException, TransformerException {

        removeAllNodes();
        
        for (Window window : windows) {
            window.writeToDOM(doc);
        }
        
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        PrintWriter writer = new PrintWriter(path.toFile());
        writer.print("");
        writer.close();

        Result result = new StreamResult(path.toFile());
        transformer.transform(new DOMSource(doc), result);
    }

    public void initWindow(JFrame frame, String name) {
        Window window = null;
        for (Window w : windows) {
            if (name.equals(w.getTitle())) {
                window = w;
            }
        }
        if (window == null) {
            window = new Window(name, DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
            windows.add(window);
        }
        frame.setSize(window.getWidth(), window.getHeight());
        frame.setLocation(window.getXPos(), window.getYPos());
    }

    public void updateWindow(JFrame frame) {
        Window window = null;
        for (Window w : windows) {
            if (frame.getTitle().equals(w.getTitle())) {
                window = w;
            }
        }
        try{
            window.setWidth(frame.getWidth());
            window.setHeight(frame.getHeight());
            window.setXPos(frame.getX());
            window.setYPos(frame.getY());
        }catch(NullPointerException ex){
            System.out.println("nullpointer while updating window");
        }
        
    }
    
    public void removeAllNodes(){
        NodeList nodes = doc.getDocumentElement().getElementsByTagName("window");
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                node.getParentNode().removeChild(node);
            }
        }
    }

}
