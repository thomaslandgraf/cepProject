package com.landgraf.cepProject.services;

import com.landgraf.cepProject.entities.Address;
import com.landgraf.cepProject.repositories.AddressRepository;
import com.landgraf.cepProject.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    public List<Address> findAll() {
        return repository.findAll();
    }

    public Address findById(Long id) {
        Optional<Address> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    
    public Address insert(Address obj) {
        return repository.save(obj);
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    public Address update(Long id, Address obj) {
        Address entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Address entity, Address obj) {
        entity.setCep(obj.getCep());
        entity.setCity(obj.getCity());
        entity.setComplement(obj.getComplement());
        entity.setNeighborhood(obj.getNeighborhood());
        entity.setNumber(obj.getNumber());
        entity.setState(obj.getState());
        entity.setStreet(obj.getStreet());
        entity.setIbge(obj.getIbge());
    }
}
