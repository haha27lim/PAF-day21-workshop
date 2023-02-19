package sg.edu.nus.iss.app.day21workshopSqlRowSet.model;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

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
    private String orderDate; // LocalDateTime have problem on SqlRowSet for TimeStamp (DateTime field in MySQL)
    private String shippedDate; // LocalDateTime have problem on SqlRowSet for TimeStamp (DateTime field in MySQL)
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
    private String paidDate; // LocalDateTime have problem on SqlRowSet for TimeStamp (DateTime field in MySQL)
    private String notes;
    private Double taxRate;
    private Byte taxStatusId;
    private Byte statusId;


    // This method is used to convert a result set from a database query to a Order object.
    public static Order create(SqlRowSet rs) {        
    Order ord = new Order();
    ord.setId(rs.getInt("id"));
    ord.setEmployeeId(rs.getInt("employee_id"));
    ord.setCustomerId(rs.getInt("customer_id"));
    ord.setOrderDate(rs.getString("order_date"));
    ord.setShippedDate(rs.getString("shipped_date"));
    ord.setShipperId(rs.getInt("shipper_id"));
    ord.setShipName(rs.getString("ship_name"));
    ord.setShipAddress(rs.getString("ship_address"));
    ord.setShipCity(rs.getString("ship_city"));
    ord.setShipStateProvince(rs.getString("ship_state_province"));
    ord.setShipZipPostalCode(rs.getString("ship_zip_postal_code"));
    ord.setShipCountryRegion(rs.getString("ship_country_region"));
    ord.setShippingFee(rs.getBigDecimal("shipping_fee"));
    ord.setTaxes(rs.getBigDecimal("taxes"));
    ord.setPaymentType(rs.getString("payment_type"));
    ord.setPaidDate(rs.getString("paid_date"));
    ord.setNotes(rs.getString("notes"));
    ord.setTaxRate(rs.getDouble("tax_rate"));
    ord.setTaxStatusId(rs.getByte("tax_status_id"));
    ord.setStatusId(rs.getByte("status_id"));
        return ord;
    }


}
