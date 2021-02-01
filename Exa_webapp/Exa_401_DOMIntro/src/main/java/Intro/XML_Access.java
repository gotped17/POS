package Intro;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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

public class XML_Access {

    private Document doc;

    public static void main(String[] args) {
        XML_Access xmla = new XML_Access();
        Path xmlPath1 = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "res", "addresses.xml");
        Path xmlPath2 = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "res", "addresses_new.xml");
        try {
            xmla.loadFromXMLFile(xmlPath1.toFile());
            //xmla.saveToXMLFile(xmlPath2.toFile());
            for (Address add : xmla.getAddressList()) {
                System.out.println(add);
            }
            xmla.removeAddressByCountry("Brazil");
            xmla.saveToXMLFile(xmlPath2.toFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //removeByAddress
    public void removeAddressByCountry(String country) {
        NodeList nl = doc.getDocumentElement().getElementsByTagName("address");
        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                elements.add((Element) node);
            }
        }
        for (Element element : elements) {
            if (element.getAttribute("country").equals(country)) {
                doc.removeChild(element);

            }
        }

    }

    //addIdAsAttributeToAddress
    public void addIdAsAttributeToAddress() {
        NodeList nl = doc.getDocumentElement().getElementsByTagName("address");
        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);

            ((Element) n).setAttribute("id", String.valueOf(i + 1));
        }
    }

    //createDOMByGender
    public void createDOMByGender() {

    }

    public Address convertNodeToAddress(Element e) {
        String name = e.getElementsByTagName("name").item(0).getTextContent();
        String country = e.getElementsByTagName("country").item(0).getTextContent();
        String city = e.getElementsByTagName("city").item(0).getTextContent();
        String email = e.getElementsByTagName("email").item(0).getTextContent();
        String gender = e.getElementsByTagName("gender").item(0).getTextContent();
        String timezone = e.getElementsByTagName("timezone").item(0).getTextContent();
        return new Address(name, country, city, email, gender, timezone);
    }

    public List<Address> getAddressList() {
        List<Address> adrList = new ArrayList<>();
        NodeList nl = doc.getDocumentElement().getElementsByTagName("address");
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                adrList.add(convertNodeToAddress((Element) node));
            }
        }
        return adrList;
    }

    /**
     * Deserialization of xml Document from file into DOM Object
     *
     * @param file
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public void loadFromXMLFile(File file) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        doc = builder.parse(file);
    }

    /**
     * Serialization of Java-DOM-Object into XML File
     *
     * @param file
     * @throws TransformerConfigurationException
     * @throws TransformerException
     */
    public void saveToXMLFile(File file) throws TransformerConfigurationException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute("indent-number", 2);
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        Result result = new StreamResult(file);
        transformer.transform(new DOMSource(doc), result);
    }

}
