package sg.edu.nus.iss.app.day21workshopSqlRowSet.controller;

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

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.app.day21workshopSqlRowSet.model.Customer;
import sg.edu.nus.iss.app.day21workshopSqlRowSet.model.Order;
import sg.edu.nus.iss.app.day21workshopSqlRowSet.repository.CustomerRepository;


@RestController
@RequestMapping(path="/api", produces= MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    
    @Autowired
    private CustomerRepository custRepo;

    // Method maps HTTP GET requests to the endpoint /api/customers and returns a JSON response containing a list of customers
    @GetMapping(path="/customers")
    public ResponseEntity<String> getAllCustomer (@RequestParam(required=false, defaultValue= "5") Integer limit,
        @RequestParam(required=false, defaultValue="0") Integer offset) {

        // Get a list of customers from the repository by calling AllCustomers method and pass the limit and offset parameters
        List<Customer> customers = custRepo.AllCustomers (limit, offset);
        
        // Build a JSON array of customer objects
        JsonArrayBuilder arrBuilder  = Json.createArrayBuilder();

        for (Customer c : customers) {
            arrBuilder.add(c.toJSON());
        }
        // Convert the array to a JSON object and return it as the response
        JsonArray result = arrBuilder.build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());  

    }

    // Method maps HTTP GET requests to the endpoint /api/customer/{id} and returns the customer details as a JSON response
    @GetMapping(path="/customer/{id}")
    public ResponseEntity<String> getCustomerById(@PathVariable Integer id) {
        // Try to fetch the customer with the given ID from the database
        Customer customers = custRepo.findCustomerById(id);
        
        // If no customer is found with the given ID, return a 404 Not Found response
        if (customers== null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"Error_message\": \"Record not found\"}");
        }
    
        // Otherwise, return the customer details as a JSON object
        JsonObject customerJson = customers.toJSON();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(customerJson.toString());
    }

    // Method maps HTTP GET requests to the endpoint /api/customer/{id}/orders and returns the orders of a specific customer as a JSON response
    @GetMapping(path="/customer/{id}/orders")
    public ResponseEntity<Object> getCustomerOrders(@PathVariable int id) {

        // Try to fetch the customer with the given ID from the database
        Customer customers = custRepo.findCustomerById(id);
        // If no customer is found with the given ID, return a 404 Not Found response
        if (customers== null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"Error_message\": \"Customer does not exist\"}");
        }

        // Get a list of orders of the specific customer from the repository
        List<Order> orders = custRepo.getCustomersOrder(id);
        // If there is no order for the specific customer, return an empty list
        if (orders.isEmpty()) {
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        // Return the list of orders as a JSON object
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orders);
    }

}