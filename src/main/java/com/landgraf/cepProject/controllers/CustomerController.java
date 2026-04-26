package com.landgraf.cepProject.controllers;

import com.landgraf.cepProject.entities.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController{

    @GetMapping
    public ResponseEntity<Customer> findAll(){
        Customer customer = new Customer(1L, "Thomas", "thomas.ml@hotmail.com", "1231564");
        return ResponseEntity.ok().body(customer);
    }
}
