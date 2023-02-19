package sg.edu.nus.iss.app.workshop21.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.app.workshop21.model.Customer;
import sg.edu.nus.iss.app.workshop21.model.Order;
import sg.edu.nus.iss.app.workshop21.repo.CustomerRepository;

@RestController
@RequestMapping("/api")
public class CustomerController {
    
    // declare a private instance variable for the customer repository
    @Autowired
    private CustomerRepository customerRepo;

    // handle GET requests to "/api/customers"
    @GetMapping(path="/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(value = "limit", required = false, defaultValue = "5") Integer limit,
    @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) {
        
        // list to store customers
        List<Customer> listCustomer = new ArrayList<Customer>();
        
        // retrieve customers from the repository
        listCustomer = customerRepo.findAll(limit, offset);

        // check if there are any customers
        if (listCustomer.isEmpty()){
            // return a 404 Not Found response if there are no customers
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // return the list of customers with a 200 OK response
            return new ResponseEntity<>(listCustomer, HttpStatus.OK);
        }   
    }    

    // handle GET requests to "/api/customer/{id}"
    @GetMapping(path="/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCustomerDetails(@PathVariable int id) {

        // check if the ID is a positive integer
        if (id < 1) {
            // return a 400 Bad Request response if the ID is not valid
            return new ResponseEntity<>("Invalid customer ID", HttpStatus.BAD_REQUEST);
        }

        // retrieve the customer with the specified ID
        Customer customer = customerRepo.findCustomerById(id);
        // check if the customer exists        
        if (customer == null) {
            // return a 404 Not Found response if the customer does not exist
            return new ResponseEntity<>("Customer with ID " + id + " not found", HttpStatus.NOT_FOUND);
        } // return the customer with a 200 OK response
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    // handle GET requests to "/api/customer/{id}/orders"
    @GetMapping(path="/customer/{id}/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCustomerOrders(@PathVariable int id) {

        // check if the ID is a positive integer
        if (id < 1) {
            // return a 400 Bad Request response if the customer ID is not a positive integer
            return new ResponseEntity<>("Invalid customer ID", HttpStatus.BAD_REQUEST);
        }

        // retrieves the customer with the specified ID from the repository
        Customer customer = customerRepo.findCustomerById(id);
        // checks if the customer exists
        if (customer == null) {
            // returns a 404 NOT_FOUND response if the customer does not exist in the repository
            return new ResponseEntity<>("Customer with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
        // retrieves the orders for the specified customer from the repository
        List<Order> orders = customerRepo.getCustomersOrder(id);
        // return the customer with a 200 OK response
        return new ResponseEntity<>(orders, HttpStatus.OK);
         
    }
}

