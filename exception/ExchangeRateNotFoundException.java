package com.company.project.services.exception;

public class ExchangeRateNotFoundException extends RuntimeException{
    public ExchangeRateNotFoundException(String msg) {

        super(msg);
    }
}
