package io.github.ital023.CustomerConnect.service;

import io.github.ital023.CustomerConnect.controller.dto.CreateCustomerDto;
import io.github.ital023.CustomerConnect.controller.dto.UpdateCustomerDto;
import io.github.ital023.CustomerConnect.entity.CustomerEntity;
import io.github.ital023.CustomerConnect.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

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


    public Page<CustomerEntity> findAll(Integer page, Integer pageSize, String orderBy, String cpf, String email) {

        var pageRequest = getPageRequest(page, pageSize, orderBy);

        return findWithFilter(cpf, email, pageRequest);
    }

    private Page<CustomerEntity> findWithFilter(String cpf, String email, PageRequest pageRequest) {
        if(hasText(cpf) && hasText(email)) {
            return customerRepository.findByCpfAndEmail(cpf, email, pageRequest);
        }
        if(hasText(cpf)) {
            return customerRepository.findByCpf(cpf, pageRequest);
        }
        if(hasText(email)) {
            return customerRepository.findByEmail(email, pageRequest);
        }

        return customerRepository.findAll(pageRequest);
    }


    private PageRequest getPageRequest(Integer page, Integer pageSize, String orderBy) {
        var direction = Sort.Direction.DESC;
        if(orderBy.equalsIgnoreCase("asc")) {
            direction = Sort.Direction.ASC;
        }

        return PageRequest.of(page, pageSize, direction, "createdAt");
    }


    public Optional<CustomerEntity> findById(Long customerId) {

        return customerRepository.findById(customerId);
    }


    public Optional<CustomerEntity> updateById(Long customerId, UpdateCustomerDto dto) {

        var customer = customerRepository.findById(customerId);

        if(customer.isPresent()) {
            updateFields(dto, customer);
            customerRepository.save(customer.get());
        }

        return customer;
    }

    private void updateFields(UpdateCustomerDto dto, Optional<CustomerEntity> customer) {
        if (hasText(dto.fullName())) {
            customer.get().setFullName(dto.fullName());
        }
        if (hasText(dto.email())) {
            customer.get().setEmail(dto.email());
        }
        if (hasText(dto.phoneNumber())) {
            customer.get().setPhoneNumber(dto.phoneNumber());
        }
    }

    public boolean deleteById(Long customerId) {

        var exists = customerRepository.existsById(customerId);

        if(exists) {
            customerRepository.deleteById(customerId);
        }

        return exists;
    }
}
