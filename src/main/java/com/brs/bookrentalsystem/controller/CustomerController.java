package com.brs.bookrentalsystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.brs.bookrentalsystem.dto.Customer;
import com.brs.bookrentalsystem.service.CustomerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{custId}")
    public Customer getCustomerById(@PathVariable("custId") int custId) {
        return customerService.getCustomerById(custId);
    }

    @PostMapping("/register")
    public ResponseEntity<String> insertCustomer(@RequestBody Customer customer) {
        String successMessage = customerService.insertCustomer(customer);
        return ResponseEntity.ok(successMessage);
    }

    @PutMapping("/{custId}")
    public void updateCustomer(@PathVariable("custId") int custId, @RequestBody Customer customer) {
        customer.setCustId(custId);
        customerService.updateCustomer(custId, customer);
    }

    @DeleteMapping("/{custId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("custId") int custId) {
        String response = customerService.deleteCustomer(custId);
        if (response.contains("Cannot delete")) {
            return ResponseEntity.status(400).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
