package com.landgraf.cepProject.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//TODO REMOMVA O EXCEPTION HANDLER E UTILIZE O RESPONSE STATUS DIRETO AQUI NA EXCEPTION
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("Resource not found with the following parameter: " + id);
    }

}
