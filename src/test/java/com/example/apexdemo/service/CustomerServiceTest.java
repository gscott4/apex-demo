package com.example.apexdemo.service;

import com.example.apexdemo.controller.CustomerController;
import com.example.apexdemo.dto.CustomerDto;
import com.example.apexdemo.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceTest {

    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    public void startUp() {

        customerService = new CustomerService(customerRepository);
    }

    @Test
    public void findCustomerById() {
        CustomerDto customerDto = customerService.getCustomerById((long) 1);
        assertNotNull(customerDto);
    }


}