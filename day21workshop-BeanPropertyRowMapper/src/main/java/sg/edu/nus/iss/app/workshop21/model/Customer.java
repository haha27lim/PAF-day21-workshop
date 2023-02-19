package sg.edu.nus.iss.app.workshop21.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Integer id;
    private String company;
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;
    private String businessPhone;
    private String homePhone;
    private String mobilePhone;
    private String faxNumber;
    private String address;
    private String city;
    private String stateProvince;
    private String zipPostalCode;
    private String countryRegion;
    private String webPage;
    private String notes;
    private byte[] attachments;
    
}
