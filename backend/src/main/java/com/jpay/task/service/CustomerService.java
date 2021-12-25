package com.jpay.task.service;

import com.jpay.task.data.Customer;
import com.jpay.task.models.CustomerModel;
import com.jpay.task.repository.CustomerRepository;
import com.jpay.task.util.PhoneNumbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> readCustomers(){
        return customerRepository.findAll();
    }

    public List<CustomerModel> getCustomers(){
         List<Customer> customersList = customerRepository.findAll();
         return getCustomersCountries(customersList);
    }

    private List<CustomerModel> getCustomersCountries(List<Customer> customers) {
        List<CustomerModel> customerAppModelList = new ArrayList<CustomerModel>();
        for (Customer customer : customers) {
            CustomerModel customerAppModel = new CustomerModel();
            customerAppModel.setId(customer.getId());
            customerAppModel.setName(customer.getName());
            customerAppModel.setPhoneNumber(customer.getPhone());
            for (PhoneNumbers phone : PhoneNumbers.values()) {
                if (Pattern.compile(phone.getCountryRegex()).matcher(customer.getPhone()).matches()) {
                    customerAppModel.setCountryName(phone.toString());
                    if (Pattern.compile(phone.getValidRegex()).matcher(customer.getPhone()).matches()) {
                        customerAppModel.setState("Valid");
                    }
                    else
                        customerAppModel.setState("Not Valid");
                    break;
                }
            }
            customerAppModelList.add(customerAppModel);
        }
        return customerAppModelList;
    }
}
