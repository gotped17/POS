/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Gottl
 */
@Data
@AllArgsConstructor
public class Window {
    private String title;
    private int xPos, yPos, width, height;
    
 
    public Window(Element node){
        title = node.getAttribute("name");
        xPos = Integer.parseInt(node.getElementsByTagName("xpos").item(0).getTextContent());
        yPos = Integer.parseInt(node.getElementsByTagName("ypos").item(0).getTextContent());
        width = Integer.parseInt(node.getElementsByTagName("width").item(0).getTextContent());
        height = Integer.parseInt(node.getElementsByTagName("height").item(0).getTextContent());
    }
    /*
    Used to add a new node to the dom document
    Document doc is the dom document in which the node should be created
    */
    public void writeToDOM(Document doc){
     
        Element elem = doc.createElement("window");
        Element xElem = doc.createElement("xpos");
        xElem.setTextContent(this.xPos+"");
        Element yElem = doc.createElement("ypos");
        yElem.setTextContent(this.yPos+"");
        Element widthElem = doc.createElement("width");
        widthElem.setTextContent(this.width+"");
        Element heightElem = doc.createElement("height");
        heightElem.setTextContent(this.height+"");
        
        elem.setAttribute("name", title);
        elem.appendChild(xElem);
        elem.appendChild(yElem);
        elem.appendChild(widthElem);
        elem.appendChild(heightElem);
        
        doc.getDocumentElement().appendChild(elem);
    }
}
