package io.github.ital023.CustomerConnect.controller.dto;

public record CreateCustomerDto(String fullName,
                                String cpf,
                                String email,
                                String phoneNumber) {
}
