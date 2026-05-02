package com.landgraf.cepProject.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("Resource not found with the following parameter: " + id);
    }

}
