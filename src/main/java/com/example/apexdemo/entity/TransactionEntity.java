package com.example.apexdemo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

import static com.example.apexdemo.constant.TransactionConstants.*;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "transaction_name")
    private String transactionName;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column(name = "transaction_price")
    private double transactionPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private CustomerEntity customerEntity;

    /**
     * Generated Points Field
     */
    @Transient
    private int points;

    @PostLoad
    private void onLoad() {
        this.points = calculatePointsForTransaction((int) transactionPrice);
    }

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

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public int getPoints() {
        return this.points;
    }

    /**
     * Immutable function to calculate the points for a given transaction
     * @param transactionAmount
     * @return - points property
     */
    final int calculatePointsForTransaction(int transactionAmount) {
        int points = 0;
        if(transactionAmount > TRANSACTION_LIMIT_FOR_TIER1_REWARD && transactionAmount <= TRANSACTION_LIMIT_FOR_TIER2_REWARD) {
            points = (transactionAmount - TRANSACTION_LIMIT_FOR_TIER1_REWARD) * TIER1_POINTS;
        }
        else if(transactionAmount > TRANSACTION_LIMIT_FOR_TIER2_REWARD) {
            points += TIER1_POINTS * TRANSACTION_LIMIT_FOR_TIER1_REWARD;
            int tier2Points = (transactionAmount - TRANSACTION_LIMIT_FOR_TIER2_REWARD) * TIER2_POINTS;
            points += tier2Points;
        }
        return points;
    }

}
