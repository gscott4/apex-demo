package com.example.apexdemo.controller;

import com.example.apexdemo.dto.CustomerDto;
import com.example.apexdemo.entity.CustomerEntity;
import com.example.apexdemo.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class CustomerControllerTest {

    private final CustomerController customerController;

    @Autowired
    public CustomerControllerTest(CustomerService customerService) {
        this.customerController = new CustomerController(customerService);
    }

    @Test
    public void findCustomerById_handleSuccess() {

        CustomerDto customerDto = customerController.getCustomerById(1);
        assertNotNull(customerDto);

        assertEquals(customerDto.getCustomerName(), "Grayson");

    }

    @Test
    public void createCustomer_handleSuccess() {
        Long val = Long.valueOf(1);
        CustomerEntity customerEntity = new CustomerEntity(val, "Java");
        ResponseEntity<CustomerEntity> responseEntity = customerController.createCustomer(customerEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

}