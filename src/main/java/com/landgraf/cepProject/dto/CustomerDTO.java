package com.landgraf.cepProject.dto;

import com.landgraf.cepProject.entities.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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


}
