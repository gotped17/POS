package at.htlkaindorf.beans;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gottl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Channel {
    @XmlElement(name="title")
    private String title;
    @XmlElement(name="link")
    private String link;
    @XmlElement(name="description")
    private String description;
    @XmlElement(name="language")
    private String language;
    @XmlElement(name="image")
    private Image image;
    //@XmlElement(name="item")
    private List<Item> item;
}
