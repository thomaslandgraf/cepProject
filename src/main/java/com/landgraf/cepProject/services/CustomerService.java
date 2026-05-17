package com.landgraf.cepProject.services;


import com.landgraf.cepProject.dto.AddressDTO;
import com.landgraf.cepProject.dto.CustomerDTO;
import com.landgraf.cepProject.entities.Address;
import com.landgraf.cepProject.entities.Customer;
import com.landgraf.cepProject.repositories.CustomerRepository;
import com.landgraf.cepProject.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    //TODO
    private final CustomerRepository repository;

    private final RestClient restClient;

    public List<Customer> findAll(){
        return repository.findAll();
    }

    public Customer findById(Long id) {
        Optional<Customer> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Customer findByDocument(String document) {
        Optional<Customer> obj = repository.findByDocument(document);
        return obj.orElseThrow(() -> new ResourceNotFoundException(document));
    }

    public Customer insert(CustomerDTO dto) {

        if (repository.existsByDocument(dto.getDocument())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Document already exists.");
        }

        String cleanCep = dto.getCep().replaceAll("\\D", "");

        if (cleanCep.length() != 8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid CEP format.");
        }

        
        //TODO na inserção de novos endereços adicionar o novo atributo integration_type que pode valer rest ou feign esse atributo será um QUERY_PARAM
        //INTEGRATION_TYPE não pode ser de preenchimento obrigatório, caso esteja vazio ou nullo será utilizada a integração FEIGN
        //Novo service de integração com via cep
        //RECEBE String CEP, IntegrationType = REST / FEIGN
        //IntegrationType  = Rest usa RESTCLIENT;
        //IntegrationType = FEIGN usa FEIGNCLIENT;
        //RETORNA AddressResponse
        
        
        AddressDTO addressDTO = restClient.get()
                .uri("/{cep}/json/", dto.getCep())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new ResourceNotFoundException("CEP not found or invalid.");
                })
                .body(AddressDTO.class);

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setDocument(dto.getDocument());

        Address address = new Address();
        address.setCep(dto.getCep());
        address.setStreet(addressDTO.getLogradouro());
        address.setNeighborhood(addressDTO.getBairro());
        address.setCity(addressDTO.getLocalidade());
        address.setState(addressDTO.getUf());
        address.setNumber(dto.getNumber());
        address.setComplement(dto.getComplement());

        address.setCustomer(customer);
        customer.getAddress().add(address);

        return repository.save(customer);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Customer update(Long id, CustomerDTO obj) {
        Customer entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Customer entity, CustomerDTO obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setDocument(obj.getDocument());
    }

    public List<Customer> findByFilters(String cep, String name, String email, String document) {
        return repository.findByFilters(cep, name, email, document);
    }
}
