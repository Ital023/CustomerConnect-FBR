package io.github.ital023.CustomerConnect.service;

import io.github.ital023.CustomerConnect.controller.dto.CreateCustomerDto;
import io.github.ital023.CustomerConnect.entity.CustomerEntity;
import io.github.ital023.CustomerConnect.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public CustomerEntity createCustomer(CreateCustomerDto dto) {

        var entity = new CustomerEntity();
        entity.setFullName(dto.fullName());
        entity.setEmail(dto.email());
        entity.setCpf(dto.cpf());
        entity.setPhoneNumber(dto.phoneNumber());

        return customerRepository.save(entity);
    }


}
