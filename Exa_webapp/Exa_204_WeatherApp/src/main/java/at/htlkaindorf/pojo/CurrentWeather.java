/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
@XmlRootElement(name="current")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrentWeather {
    @EqualsAndHashCode.Exclude
    private City city;
    private Temperature temperature;
    @XmlElement(name="pressure")
    private ValueUnitPair airPressure;
    private ValueUnitPair humidity;
    private String clouds;
    @EqualsAndHashCode.Exclude
    private Wind wind;
    @EqualsAndHashCode.Exclude
    private Weather weather;
}
