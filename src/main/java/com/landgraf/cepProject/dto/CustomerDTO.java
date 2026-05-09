package com.landgraf.cepProject.dto;

import com.landgraf.cepProject.entities.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CustomerDTO {

    @NotBlank(message = "Name is required.")
    private String name;

    @Email(message = "Email invalid.")
    @NotBlank(message = "Email is required.")
    private String email;

    @NotBlank(message = "Document is required.")
    private String document;

    @NotBlank(message = "Zip Code is required.")
    @Pattern(regexp = "\\d{8}", message = "CEP must contain exactly 8 digits.")
    private String cep;

    @NotBlank(message = "Number is required.")
    private String number; // Campo manual

    private String complement;

    public CustomerDTO(){

    }

    public CustomerDTO(Customer obj) {
        this.name = obj.getName();
        this.email = obj.getEmail();
        this.document = obj.getDocument();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}
