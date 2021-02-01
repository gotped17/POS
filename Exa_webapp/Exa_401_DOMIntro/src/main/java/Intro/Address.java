package Intro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String name;
    private String country;
    private String city;
    private String email;
    private String gender;
    private String timezone;
}
