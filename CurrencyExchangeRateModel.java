package com.company.project.services.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CurrencyExchangeRateModel {

    String fromCurrency;
    String toCurrency;
    int exchangeRate;
    String reportedOn;

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public int getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(int exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getReportedOn() {
        return reportedOn;
    }

    public void setReportedOn(String reportedOn) {
        this.reportedOn = reportedOn;
    }
}
