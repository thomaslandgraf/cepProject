package com.landgraf.cepProject.config;

import com.landgraf.cepProject.entities.Address;
import com.landgraf.cepProject.entities.Customer;
import com.landgraf.cepProject.repositories.AddressRepository;
import com.landgraf.cepProject.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void run(String... args) throws Exception {
        Customer customer1 = new Customer(null, "Thomas", "thomas@gmail.com", "45124454");
        Customer customer2 = new Customer(null, "Maria", "maria@gmail.com", "447412356488");

        Address address1 = new Address(null, "94455", "Avenida Brasil", "114", "Ao lado da churrascaria", "Centro", "Rio de Janeiro", "Rio de Janeiro", "555214");
        Address address2 = new Address(null, "77465", "Felix", "554", "APT 3332", "Centro", "Sao Paulo", "Sao Paulo", "11147");


        address1.setCustomer(customer1);
        address2.setCustomer(customer2);
        customer1.getAddress().add(address1);
        customer2.getAddress().add(address2);

        customerRepository.saveAll(Arrays.asList(customer1, customer2));
        addressRepository.saveAll(Arrays.asList(address1, address2));

    }
}
