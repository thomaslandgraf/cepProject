package com.landgraf.cepProject.controllers;

import com.landgraf.cepProject.dto.CustomerDTO;
import com.landgraf.cepProject.entities.Customer;
import com.landgraf.cepProject.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
@RequiredArgsConstructor
@Tag(name = "Customer", description = "Register customers.")
public class CustomerController{

    private final CustomerService service;

    @GetMapping
    public ResponseEntity<List<Customer>> findAll(){
        List<Customer> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        Customer obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/document/{document}")
    public ResponseEntity<Customer> findByDocument(@PathVariable String document) {
        Customer obj = service.findByDocument(document);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @Operation(summary = "Register customer", description = "Add a new customer in the database.")
    public ResponseEntity<Customer> insert(@Valid @RequestBody CustomerDTO dto) {

        Customer obj = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    //Erro ao buscar id invalido.
    @PutMapping(value = "/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
