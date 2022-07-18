package com.example.apexdemo.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionEntityTest {

    TransactionEntity transactionEntity = new TransactionEntity();

    @Test
    public void calculatePointsForTransaction_Under50() {

        int points = transactionEntity.calculatePointsForTransaction(40);
        assertEquals(0, points);

    }

    @Test
    public void calculatePointsForTransaction_Over50_Under100() {

        int points = transactionEntity.calculatePointsForTransaction(99);
        assertEquals(49, points);
    }

    @Test
    public void calculatePointsForTransaction_Over100() {

        int points = transactionEntity.calculatePointsForTransaction(120);
        assertEquals(90, points);
    }

    @Test
    public void calculatePointsForTransaction_RoundDown_Decimal() {
        double val = 120.99;
        int points = transactionEntity.calculatePointsForTransaction((int) val);
        assertEquals(90, points);
    }

}