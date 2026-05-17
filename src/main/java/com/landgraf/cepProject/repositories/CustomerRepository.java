package com.landgraf.cepProject.repositories;

import com.landgraf.cepProject.entities.Customer;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByDocument(String document);
    boolean existsByDocument(String document);

    @Query("SELECT c FROM Customer c LEFT JOIN c.address a WHERE " +
            "(:cep IS NULL OR a.cep = :cep) AND " +
            "(:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:email IS NULL OR c.email = :email) AND " +
            "(:document IS NULL OR c.document = :document)")
    List<Customer> findByFilters(
            @Param("cep") String cep,
            @Param("name") String name,
            @Param("email") String email,
            @Param("document") String document
    );
}
