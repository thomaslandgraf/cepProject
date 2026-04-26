package com.landgraf.cepProject.repositories;

import com.landgraf.cepProject.entities.Address;
import com.landgraf.cepProject.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByDocument(String document);
}
