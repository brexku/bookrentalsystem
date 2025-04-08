package com.brs.bookrentalsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brs.bookrentalsystem.dto.Customer;
import com.brs.bookrentalsystem.dto.Rental;
import com.brs.bookrentalsystem.mapper.CustomerMapper;
import com.brs.bookrentalsystem.mapper.RentalMapper;

@Service
public class CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    public CustomerService(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Autowired
    private RentalMapper rentalMapper;

    public List<Customer> getAllCustomers() {
        return customerMapper.getAllCustomers();
    }

    public Customer getCustomerById(int custId) {
        return customerMapper.getCustomerById(custId);

    }

    public String insertCustomer(Customer customer) {
        customerMapper.insertCustomer(customer);
        return String.format("Customer registered successfully, customer Id is: %d", customer.getCustId());
    }

    public void updateCustomer(int custId, Customer customer) {
        customer.setCustId(custId);
        customerMapper.updateCustomer(customer);
    }

    public String deleteCustomer(int custId) {
        Customer customer = customerMapper.getCustomerById(custId);
        if (customer == null) {
            return "Customer with id " + custId + " does not exist!";
        }

        List<Rental> customerRentals = rentalMapper.getRentalsByCustomerId(custId);
        if (!customerRentals.isEmpty()) {
            return "Cannot delete customer with id " + custId + " because they have an active rental!";
        }

        customerMapper.deleteCustomer(custId);
        return "Customer with id " + custId + " deleted successfully!";
    }

}
