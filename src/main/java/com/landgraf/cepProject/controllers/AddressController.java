package com.landgraf.cepProject.controllers;

import com.landgraf.cepProject.dto.AddressDTO;
import com.landgraf.cepProject.entities.Address;
import com.landgraf.cepProject.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/addresses")
@Tag(name = "Address", description = "Register addresses.")
public class AddressController {

    private final AddressService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List all addresses.", description = "Return a full list of all addresses registered.")
    public List<Address> findAll() {
        return service.findAll();
    }
    
    @GetMapping(value = "/{id}")
    @Operation(summary = "Find address by ID.", description = "Search a specific address by a numeric ID.")
    //TODO verificar possibilidades de melhoria de resposta em caso de type mistmatch na chamada, ao invez de um simples bad request. Acrediot que você terá que colocar o atributo dentro de um objeto e utilizar o @Validate + Constraints
    public ResponseEntity<Address> findById(@PathVariable Long id) {
        Address obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    //TODO CRIAR NOVO FIND QUE DEVE DAR A POSSIBILIDADE DE FILTROS POR QUERY_PARAM, ESSES FILTROS DEVEM SER, CEP, NOME DO CUSTOMER, EMAIL E DOCUMENT
    //FAZER ALGO SIMILAR PARA O CUSTOMER

    @GetMapping(value = "/search")
    @Operation(summary = "Search addresses with filters", description = "Filter address by CEP, name of customer, email or document.")
    public ResponseEntity<List<Address>> findByFilters(
            @RequestParam(required = false) String cep,
            @RequestParam(required = false) String customerName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String document) {

        List<Address> list = service.findByFilters(cep, customerName, email, document);
        return ResponseEntity.ok(list);
    }
    
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete address by ID.", description = "Delete an address in the database")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Operation(summary = "Create address.", description = "Register a new address in the database.")
    public ResponseEntity<Address> insert(@RequestBody AddressDTO dto) {

        Address address = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).body(address);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update address by ID.", description = "Update address information in the database.")
    public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody AddressDTO dto) {

        Address address = service.update(id, dto);
        return ResponseEntity.ok().body(address);
    }
}
