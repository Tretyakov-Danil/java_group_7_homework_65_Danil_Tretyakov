package edu.attractor.onlinestore.exceptions;

import java.io.FileNotFoundException;

public class ResourceNotFoundException extends FileNotFoundException {
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String s) {
        super(s);
    }
}
