package com.jpay.task.controller;

import com.jpay.task.data.Customer;
import com.jpay.task.models.CustomerModel;
import com.jpay.task.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    private CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/getcountrycustomers", method = RequestMethod.GET)
    public List<CustomerModel> getCountryCustomer(){
        return customerService.getCustomers();
    }

}
