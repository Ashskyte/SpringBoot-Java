package org.cna.user.service.userservice.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource not found in server!!");
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
