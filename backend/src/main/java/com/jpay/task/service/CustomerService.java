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

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository)
    {
       this.customerRepository = customerRepository;
    }
    /*
    Reads all customers from DB and return the CustomerModel list with country and state
     */
    public List<CustomerModel> getCustomers(){
         List<Customer> customersList = customerRepository.findAll();
         return getCustomersCountries(customersList);
    }

    /*
    Takes list of DB customers and map them to list of CustomerModels
     */
    private List<CustomerModel> getCustomersCountries(List<Customer> customers) {
        List<CustomerModel> customerAppModelList = new ArrayList<CustomerModel>();
        for (Customer customer : customers) {
            customerAppModelList.add(mapDbModelToAppModel(customer));
        }
        return customerAppModelList;
    }

    /*
    Map between db model to app model
     */
    private CustomerModel mapDbModelToAppModel(Customer customer)
    {
        CustomerModel customerAppModel = new CustomerModel();
        customerAppModel.setId(customer.getId());
        customerAppModel.setName(customer.getName());
        customerAppModel.setPhoneNumber(customer.getPhone());
        PhoneNumbers phone = getCountryByPhone(customer.getPhone());
        if(phone != null) {
            customerAppModel.setCountryName(phone.toString());
            customerAppModel.setState(isValid(customer.getPhone(), phone.getValidRegex()) ? "Valid" : "Not Valid");
        }
        return customerAppModel;
    }

    /*
    Get Country from phone number
     */
    private PhoneNumbers getCountryByPhone(String phoneNumber){
        for (PhoneNumbers phone : PhoneNumbers.values()) {
            if (Pattern.compile(phone.getCountryRegex()).matcher(phoneNumber).matches()) {
                return phone;
            }
        }
        return null;
    }

    /*
    Check if country number is valid or not
     */
    private boolean isValid(String phoneNumber , String regex)
    {
        if (Pattern.compile(regex).matcher(phoneNumber).matches()) {
            return true;
        }
        else
            return false;
    }
}
