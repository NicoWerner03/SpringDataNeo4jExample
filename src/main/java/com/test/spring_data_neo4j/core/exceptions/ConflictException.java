package com.test.spring_data_neo4j.core.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {super(message);}
}
