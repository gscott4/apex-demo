package com.example.apexdemo.service;

import com.example.apexdemo.dto.CustomerDto;
import com.example.apexdemo.dto.TransactionDto;
import com.example.apexdemo.entity.CustomerEntity;
import com.example.apexdemo.entity.TransactionEntity;
import com.example.apexdemo.exception.CustomerNotFoundException;
import com.example.apexdemo.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public List<CustomerDto> getAllCustomers() {
        List<CustomerEntity> customerList = customerRepository.findAll();

        return customerList.stream().map(this::convertCustomerToDto).collect(Collectors.toList());
    }

    public CustomerDto getCustomerById(Long id) {
        Optional<CustomerEntity> optCustomer = this.customerRepository.findById(id);

        if(optCustomer.isEmpty()) {
            throw new CustomerNotFoundException("id-" + id + " not found");
        }

        CustomerDto customerDto = convertCustomerToDto(optCustomer.get());
        return customerDto;

    }

    public CustomerEntity createCustomer(CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity);
    }

    public List<TransactionEntity> findTransactionsForCustomer(long id) {
        Optional<CustomerEntity> optCustomer = this.customerRepository.findById(id);
        if(optCustomer.isEmpty()) {
            throw new CustomerNotFoundException("id-" + id + " not found");
        }
        return optCustomer.get().getTransactions();
    }

    private CustomerDto convertCustomerToDto(CustomerEntity customerEntity) {
        List<TransactionDto> transactionDtoList = customerEntity.getTransactions().stream()
                .map(this::convertTransactionEntityToDto)
                .collect(Collectors.toList());

        CustomerDto customerDto = modelMapper.map(customerEntity, CustomerDto.class);
        customerDto.setTransactionDtoList(transactionDtoList);
        customerDto.setCustomerPoints(retrievePointsForCustomer(transactionDtoList));
        return customerDto;
    }

    private TransactionDto convertTransactionEntityToDto(TransactionEntity transactionEntity) {
        return modelMapper.map(transactionEntity, TransactionDto.class);
    }

    // Retrieve Points for Customer -> Packages Map of Points per month and Total Points
    private Map<String, Integer> retrievePointsForCustomer(List<TransactionDto> transactionList) {

        int pointsTwoMonthsAgo = retrievePointsForTimePeriod(getStartDateForMonth(2),
                getEndDateForMonth(2), transactionList);

        int pointsOneMonthAgo = retrievePointsForTimePeriod(getStartDateForMonth(1),
                getEndDateForMonth(1), transactionList);

        int pointsCurrentMonth = retrievePointsForTimePeriod(getStartDateForMonth(0),
                getEndDateForMonth(0), transactionList);

        int totalPoints = pointsTwoMonthsAgo + pointsOneMonthAgo + pointsCurrentMonth;

        Map<String, Integer> pointsMap = new HashMap<>();
        pointsMap.put("pointsTwoMonthsAgo", pointsTwoMonthsAgo);
        pointsMap.put("pointsOneMonthAgo", pointsOneMonthAgo);
        pointsMap.put("pointsCurrentMonth", pointsCurrentMonth);
        pointsMap.put("totalPoints", totalPoints);

        return pointsMap;
    }

    private int retrievePointsForTimePeriod(LocalDate startDate, LocalDate endDate, List<TransactionDto> transactionList) {

        List<TransactionDto> filteredList = transactionList.stream()
                .filter(transaction -> transaction.getTransactionDate().isBefore(endDate)
                        && transaction.getTransactionDate().isAfter(startDate))
                .collect(Collectors.toList());

        int points = 0;
        for (TransactionDto transactionEntity : filteredList) {
            // Will always round double value down to int (ex: $120.99 = 120)
            points += transactionEntity.getPoints();
        }

        return points;
    }

     LocalDate getStartDateForMonth(int subtractedMonths) {
        LocalDate localDateOneMonth = LocalDate.now().minusMonths(subtractedMonths);
        return localDateOneMonth.withDayOfMonth(1);
    }

     LocalDate getEndDateForMonth(int subtractedMonths) {
        LocalDate localDateOneMonth = LocalDate.now().minusMonths(subtractedMonths);
        return localDateOneMonth.withDayOfMonth(localDateOneMonth.getMonth().length(localDateOneMonth.isLeapYear()));
    }


}
