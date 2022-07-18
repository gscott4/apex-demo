package com.example.apexdemo.dto;

import com.example.apexdemo.entity.CustomerEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class CustomerDto {

    @JsonProperty("customer")
    private CustomerEntity customerEntity;
    @JsonProperty("points")
    private Map<String, Integer> points;

    public CustomerDto() {}

    public CustomerDto(CustomerEntity customerEntity, Map<String, Integer> points) {
        this.customerEntity = customerEntity;
        this.points = points;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }
}
