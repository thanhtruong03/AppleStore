package com.applestore.applestore.Services;

import com.applestore.applestore.DTOs.CustomerDto;
import com.applestore.applestore.Entities.Customer;
import com.applestore.applestore.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDto getCustomerById(int id){
        CustomerDto customerDto = new CustomerDto();
        Customer customer = customerRepository.getReferenceById(id);
        customerDto.setUser_id(customer.getUser_id());
        customerDto.setCustomer_id(customer.getCustomer_id());
        customerDto.setAddress_line(customer.getAddress_line());
        customerDto.setPhone(customer.getPhone());
        customerDto.setCity(customer.getCity());
        customerDto.setCountry(customer.getCountry());
        return customerDto;
    }
}
