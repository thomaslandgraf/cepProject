package com.landgraf.cepProject.services.exceptions;

//TODO REMOMVA O EXCEPTION HANDLER E UTILIZE O RESPONSE STATUS DIRETO AQUI NA EXCEPTION
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("Resource not found with the following parameter: " + id);
    }

}
