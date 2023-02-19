package sg.edu.nus.iss.app.day21workshopSqlRowSet.model;


import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
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
    private String attachments;


    // This method is used to convert a result set from a database query to a Customer object.
    public static Customer create(SqlRowSet rs) {
        Customer c = new Customer();
        c.setId(rs.getInt("id"));
        c.setCompany(rs.getString("company"));
        c.setFirstName(rs.getString("first_name"));
        c.setLastName(rs.getString("last_name"));
        c.setEmail(rs.getString("email_address"));
        c.setJobTitle(rs.getString("job_title"));
        c.setBusinessPhone(rs.getString("business_phone"));
        c.setHomePhone(rs.getString("home_phone"));
        c.setMobilePhone(rs.getString("mobile_phone"));
        c.setFaxNumber(rs.getString("fax_number"));
        c.setAddress(rs.getString("address"));
        c.setCity(rs.getString("city"));
        c.setStateProvince(rs.getString("state_province"));
        c.setZipPostalCode(rs.getString("zip_postal_code"));
        c.setCountryRegion(rs.getString("country_region"));
        c.setWebPage(rs.getString("web_page"));
        c.setNotes(rs.getString("notes"));
        c.setAttachments(rs.getString("attachments"));
        return c;
    }

    // This method creates a JSON object builder, adds each field to the builder, and then builds the JSON object.
    public JsonObject toJSON() {
        return Json.createObjectBuilder()
            .add("id", getId())
            .add("company", getCompany())
            .add("first_name", getFirstName())
            .add("last_name", getLastName())
            .add("email_address", getEmail() != null ? getEmail() : "")
            .add("job_title", getJobTitle())
            .add("business_phone", getBusinessPhone())
            .add("home_phone", getHomePhone() != null ? getHomePhone() : "")
            .add("mobile_phone", getMobilePhone() != null ? getMobilePhone() : "")
            .add("fax_number", getFaxNumber())
            .add("address", getAddress())
            .add("city", getCity())
            .add("city", getCity())
            .add("state_province", getStateProvince())
            .add("zip_postal_code", getZipPostalCode())
            .add("country_region", getCountryRegion())
            .add("web_page", getWebPage() != null ? getWebPage() : "")
            .add("notes", getNotes() != null ? getNotes() : "")
            .add("attachments", getAttachments())
            .build();
    }

    
}
