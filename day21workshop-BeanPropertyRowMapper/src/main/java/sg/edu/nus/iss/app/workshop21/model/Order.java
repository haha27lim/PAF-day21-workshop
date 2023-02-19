package sg.edu.nus.iss.app.workshop21.model;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    
    private Integer id;
    private Integer employeeId;
    private Integer customerId;
    private Date orderDate;
    private Date shippedDate;
    private Integer shipperId;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipStateProvince;
    private String shipZipPostalCode;
    private String shipCountryRegion;
    private BigDecimal shippingFee;
    private BigDecimal taxes;
    private String paymentType;
    private Date paidDate;
    private String notes;
    private Double taxRate;
    private Byte taxStatusId;
    private Byte statusId;

}
