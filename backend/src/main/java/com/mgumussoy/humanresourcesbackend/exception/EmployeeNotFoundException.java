package com.mgumussoy.humanresourcesbackend.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("EmployeeNotFoundException");
    }
}
