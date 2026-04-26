package com.landgraf.cepProject.repositories;

import com.landgraf.cepProject.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
