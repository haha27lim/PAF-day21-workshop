package sg.edu.nus.iss.app.workshop21.repo;

import static sg.edu.nus.iss.app.workshop21.repo.Queries.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.app.workshop21.model.Customer;
import sg.edu.nus.iss.app.workshop21.model.Order;


@Repository
public class CustomerRepository {
    
    // autowire JdbcTemplate to access database
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // method to retrieve all customers with a limit and offset
    public List<Customer> findAll(Integer limit, Integer offset) {
        // create a list to store result
        List<Customer> resultList = new ArrayList<Customer>();
        // execute the query to retrieve customers from database
        resultList = jdbcTemplate.query(SQL_SELECT_ALL_CUSTOMERS, BeanPropertyRowMapper.newInstance(Customer.class), limit, offset);
        return resultList;
    }
    
    // method to retrieve a customer by their ID
    public Customer findCustomerById(Integer id) {
        // execute the query to retrieve the customer by ID
        return jdbcTemplate.queryForObject(SQL_SELECT_CUSTOMERS_BY_ID, BeanPropertyRowMapper.newInstance(Customer.class), id);
    }

    // method to retrieve all orders of a customer by their ID
    public List<Order> getCustomersOrder(Integer id) {
        // create a list to store the result
        List<Order> orders = new ArrayList<Order>();
        // execute the query to retrieve orders by customer ID
        orders = jdbcTemplate.query(SQL_SELECT_ORDERS_CUSTOMER_ID, BeanPropertyRowMapper.newInstance(Order.class), id);
        return orders;
    }
}
