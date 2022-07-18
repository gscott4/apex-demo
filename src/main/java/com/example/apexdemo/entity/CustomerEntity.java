package com.example.apexdemo.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false, unique = true)
    private Long customerId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @OneToMany(mappedBy = "customerEntity")
    @Where(clause = "transaction_date >= DATEADD('MONTH', -3, NOW())")
    private List<TransactionEntity> transactions = new ArrayList<>();

    public CustomerEntity(Long customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public CustomerEntity() {
    }

    public void setCustomerId(Long customer_id) {
        this.customerId = customer_id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String name) {
        this.customerName = name;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

}
