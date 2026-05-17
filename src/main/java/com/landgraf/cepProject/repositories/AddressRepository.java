package com.landgraf.cepProject.repositories;

import com.landgraf.cepProject.entities.Address;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a JOIN a.customer c WHERE " +
            "(:cep IS NULL OR a.cep = :cep) AND " +
            "(:customerName IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :customerName, '%'))) AND " +
            "(:email IS NULL OR c.email = :email) AND " +
            "(:document IS NULL OR c.document = :document)")
    List<Address> findByFilters(
            @Param("cep") String cep,
            @Param("customerName") String customerName,
            @Param("email") String email,
            @Param("document") String document
    );

}
