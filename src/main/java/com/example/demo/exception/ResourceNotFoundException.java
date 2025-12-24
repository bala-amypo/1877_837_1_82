// src/main/java/com/example/demo/exception/ResourceNotFoundException.java
package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message); // must contain "not found"
    }
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
