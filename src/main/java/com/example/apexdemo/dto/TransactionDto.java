package com.example.apexdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class TransactionDto {

    @JsonProperty("transactionId")
    private Long transactionId;

    @JsonProperty("transactionName")
    private String transactionName;

    @JsonProperty("transactionDate")
    private LocalDate transactionDate;

    @JsonProperty("transactionPrice")
    private double transactionPrice;

    @JsonProperty("transactionPoints")
    private int points;

    public TransactionDto() {}

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(double transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
