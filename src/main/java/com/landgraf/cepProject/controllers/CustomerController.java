package com.landgraf.cepProject.controllers;

import com.landgraf.cepProject.dto.CustomerDTO;
import com.landgraf.cepProject.entities.Address;
import com.landgraf.cepProject.entities.Customer;
import com.landgraf.cepProject.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    @Operation(summary = "List of all Customers", description = "Return a full list of all customers with their address.")
    public ResponseEntity<List<Customer>> findAll(){
        List<Customer> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Search customer by ID.", description = "Return a specific customer by ID.")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        Customer obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/document/{document}")
    @Operation(summary = "Search customer by document.", description = "Return a specific customer by document.")
    public ResponseEntity<Customer> findByDocument(@PathVariable String document) {
        Customer obj = service.findByDocument(document);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/search")
    @Operation(summary = "Search customer with filters", description = "Filter customer by CEP, name, email or document.")
    public ResponseEntity<List<Customer>> findByFilters(
            @RequestParam(required = false) String cep,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String document) {

        List<Customer> list = service.findByFilters(cep, name, email, document);
        return ResponseEntity.ok(list);
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
    @Operation(summary = "Delete a customer.", description = "Delete a customer permanent from database.")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    //Erro ao buscar id invalido.
    @PutMapping(value = "/{id}")
    @Operation(summary = "Update a customer.", description = "Update customer's information from database")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody CustomerDTO dto) {
        Customer customer = service.update(id, dto);
        return ResponseEntity.ok().body(customer);
    }

}
