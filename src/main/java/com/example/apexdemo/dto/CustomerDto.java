package com.example.apexdemo.dto;

import com.example.apexdemo.entity.CustomerEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class CustomerDto {

    @JsonProperty("id")
    private Long customerId;

    @JsonProperty("customerName")
    private String customerName;

    @JsonProperty("transactions")
    private List<TransactionDto> transactionDtoList;

    @JsonProperty("customerPoints")
    private Map<String, Integer> customerPoints;

    public CustomerDto() {}

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<TransactionDto> getTransactionDtoList() {
        return transactionDtoList;
    }

    public void setTransactionDtoList(List<TransactionDto> transactionDtoList) {
        this.transactionDtoList = transactionDtoList;
    }

    public Map<String, Integer> getCustomerPoints() {
        return customerPoints;
    }

    public void setCustomerPoints(Map<String, Integer> customerPoints) {
        this.customerPoints = customerPoints;
    }
}
