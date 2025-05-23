package io.github.ital023.CustomerConnect.controller;

import io.github.ital023.CustomerConnect.controller.dto.ApiResponse;
import io.github.ital023.CustomerConnect.controller.dto.CreateCustomerDto;
import io.github.ital023.CustomerConnect.controller.dto.PaginationResponse;
import io.github.ital023.CustomerConnect.controller.dto.UpdateCustomerDto;
import io.github.ital023.CustomerConnect.entity.CustomerEntity;
import io.github.ital023.CustomerConnect.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CreateCustomerDto dto) {
        var customer = customerService.createCustomer(dto);


        return ResponseEntity.created(
                URI.create("/customers/" + customer.getCustomerId())).build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CustomerEntity>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                               @RequestParam(name = "orderBy", defaultValue = "desc") String orderBy,
                                                               @RequestParam(name = "cpf", required = false) String cpf,
                                                               @RequestParam(name = "email", required = false) String email) {

         var pageResp = customerService.findAll(page, pageSize, orderBy, cpf, email);

        PaginationResponse paginationResponse =
                new PaginationResponse(pageResp.getNumber(), pageResp.getSize(), pageResp.getTotalElements(), pageResp.getTotalPages());

         ApiResponse apiResponse = new ApiResponse<>(pageResp.getContent(), paginationResponse);

         return ResponseEntity.ok(apiResponse);


    }

    @GetMapping(path = "/{customerId}")
    public ResponseEntity<CustomerEntity> findById(@PathVariable("customerId") Long customerId) {

        var user = customerService.findById(customerId);

        return user.isPresent() ?
                ResponseEntity.ok(user.get()) :
                ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/{customerId}")
    public ResponseEntity<CustomerEntity> findById(@PathVariable("customerId") Long customerId,
                                                   @RequestBody UpdateCustomerDto dto) {

        var customer = customerService.updateById(customerId, dto);

        return customer.isPresent() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{customerId}")
    public ResponseEntity<CustomerEntity> deleteById(@PathVariable("customerId") Long customerId) {

        boolean deleted = customerService.deleteById(customerId);

        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

}
