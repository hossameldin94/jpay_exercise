package com.jpay.task.service;

import com.jpay.task.data.Customer;
import com.jpay.task.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> readCustomers(){
        return customerRepository.findAll();
    }

}
