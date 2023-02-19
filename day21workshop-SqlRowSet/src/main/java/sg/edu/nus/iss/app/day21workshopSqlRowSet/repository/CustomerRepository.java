package sg.edu.nus.iss.app.day21workshopSqlRowSet.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.app.day21workshopSqlRowSet.model.Customer;
import sg.edu.nus.iss.app.day21workshopSqlRowSet.model.Order;

import static sg.edu.nus.iss.app.day21workshopSqlRowSet.repository.Queries.*;

import java.util.LinkedList;
import java.util.List;



@Repository
public class CustomerRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;


    // method to retrieve all customers from the database with the given limit and offset
    public List<Customer> AllCustomers(Integer limit, Integer offset) {
        final List<Customer> result = new LinkedList<>();

        // execute the SQL query to get all customers with given limit and offset
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_ALL_CUSTOMERS, limit, offset);

        // iterate through the result set and create a Customer object for each row
        while (rs.next()) {
            result.add(Customer.create(rs));

        } // return the list of Customer objects
        return result;
    }

    // method to retrieve a customer from the database with the given ID
    public Customer findCustomerById(Integer id) {
   
         // execute the SQL query to get the customer with given ID
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_CUSTOMERS_BY_ID, id);

        // iterate through the result set and create a Customer object for the first row
        while (rs.next()) {
            return Customer.create(rs); // return the Customer object

        } // return null if no matching customer found
        return null;
        
    }


    // method to retrieve all orders of a customer by their Customer ID
    public List<Order> getCustomersOrder(Integer id) {
        // create an empty list to store the orders
        final List<Order> orders = new LinkedList<>();

        // execute the SQL query to get all orders of the customer with given Customer ID
        final SqlRowSet rs  = jdbcTemplate.queryForRowSet(SQL_SELECT_ORDERS_CUSTOMER_ID, id);

        // iterate through the result set and create an Order object for each row
        while (rs.next()) {
            orders.add(Order.create(rs));

        } // return the list of Order objects
        return orders; 
    }

}
