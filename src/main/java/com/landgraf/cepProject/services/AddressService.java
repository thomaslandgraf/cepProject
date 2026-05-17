package com.landgraf.cepProject.services;

import com.landgraf.cepProject.dto.AddressDTO;
import com.landgraf.cepProject.entities.Address;
import com.landgraf.cepProject.repositories.AddressRepository;
import com.landgraf.cepProject.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    //TODO SUBSTITUIR POR INJEÇÃO DE DEPENDENCIA VIA CONSTRUTOR, UTILIZE LOMBOK PARA CRIAR CONSTRUTORES E DEIXAR CÓDIGO MENOS VERBOSO
    private final AddressRepository repository;

    public List<Address> findAll() {
        return repository.findAll();
    }

    public Address findById(Long id) {
        Optional<Address> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    
    public Address insert(AddressDTO obj) {

        Address address = new Address();

        address.setCep(obj.getCep());
        address.setStreet(obj.getLogradouro());
        address.setComplement(obj.getComplemento());
        address.setNeighborhood(obj.getBairro());
        address.setCity(obj.getLocalidade());
        address.setState(obj.getEstado());
        address.setIbge(obj.getIbge());

        return repository.save(address);
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    public Address update(Long id, AddressDTO obj) {
        Address entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Address entity, AddressDTO obj) {
        entity.setCep(obj.getCep());
        entity.setCity(obj.getLocalidade());
        entity.setComplement(obj.getComplemento());
        entity.setNeighborhood(obj.getBairro());
        entity.setState(obj.getEstado());
        entity.setStreet(obj.getLogradouro());
        entity.setIbge(obj.getIbge());
    }

    public List<Address> findByFilters(String cep, String customerName, String email, String document) {
        return repository.findByFilters(cep, customerName, email, document);
    }
}
