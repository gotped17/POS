/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Gottl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wind {
    private WindSpeed speed;
    private Direction direction;
}
