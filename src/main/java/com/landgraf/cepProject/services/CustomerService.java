package com.landgraf.cepProject.services;


import com.landgraf.cepProject.entities.Customer;
import com.landgraf.cepProject.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> findAll(){
        return repository.findAll();
    }

    public Customer findById(Long id){
        Optional<Customer> obj = repository.findById(id);
        return obj.get();
    }
}
