package com.example.apexdemo.controller;

import com.example.apexdemo.dto.CustomerDto;
import com.example.apexdemo.entity.CustomerEntity;
import com.example.apexdemo.entity.TransactionEntity;
import com.example.apexdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    @ResponseBody
    public CustomerDto getCustomerById(@PathVariable long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerEntity> createCustomer(@RequestBody CustomerEntity customerEntity) {
        CustomerEntity savedCustomer =  customerService.createCustomer(customerEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCustomer.getCustomerId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/customers/{id}/transactions")
    @ResponseBody
    public List<TransactionEntity> findTransactionsForCustomer(@PathVariable long id) {
        return this.customerService.findTransactionsForCustomer(id);
    }

}
