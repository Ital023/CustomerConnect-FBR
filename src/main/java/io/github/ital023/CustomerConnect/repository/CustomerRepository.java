package io.github.ital023.CustomerConnect.repository;

import io.github.ital023.CustomerConnect.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
