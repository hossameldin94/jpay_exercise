package com.jpay.task;

import com.jpay.task.data.Customer;
import com.jpay.task.models.CustomerModel;
import com.jpay.task.repository.CustomerRepository;
import com.jpay.task.service.CustomerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;
    static List<Customer> customers = new ArrayList<Customer>();

    @BeforeAll
    public static void initialize()
    {
        Customer notValidCustomer = new Customer();
        notValidCustomer.setId(1);
        notValidCustomer.setName("Test user");
        notValidCustomer.setPhone("(212) 6546545369");
        customers.add(notValidCustomer);
        Customer validCustomer = new Customer();
        validCustomer.setId(2);
        validCustomer.setName("Test user2");
        validCustomer.setPhone("(212) 698054317");
        customers.add(validCustomer);

    }

    @Test
    public void assertCustomerNotValidState()
    {
        when(customerRepository.findAll()).thenReturn(customers);
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(1);
        customerModel.setName("Test user");
        customerModel.setPhoneNumber("(212) 6546545369");
        customerModel.setCountryName("MOROCCO");
        customerModel.setState("Not Valid");

        customerService = new CustomerService(customerRepository);
        List<CustomerModel> customersModel = customerService.getCustomers();
        assertThat(customersModel.get(0)).usingRecursiveComparison()
            .isEqualTo(customerModel);
    }

    @Test
    public void assertCustomerValidState()
    {
        when(customerRepository.findAll()).thenReturn(customers);
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(2);
        customerModel.setName("Test user2");
        customerModel.setPhoneNumber("(212) 698054317");
        customerModel.setCountryName("MOROCCO");
        customerModel.setState("Valid");

        customerService = new CustomerService(customerRepository);
        List<CustomerModel> customersModel = customerService.getCustomers();
        assertThat(customersModel.get(1)).usingRecursiveComparison()
                .isEqualTo(customerModel);
    }
}
