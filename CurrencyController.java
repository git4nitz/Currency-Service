package com.company.project.services.controller;

import com.instansys.datapex.exception.ExchangeRateNotFoundException;
import com.instansys.datapex.exception.InvalidDateFormatException;
import com.instansys.datapex.model.CurrencyExchangeRateModel;
import com.instansys.datapex.service.CurrencyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class CurrencyController {
    CurrencyServiceImpl currencyService;
    @Autowired
    public CurrencyController(CurrencyServiceImpl service){
        currencyService = service;
    }

    @PostMapping(value = "/currency", consumes = "application/json")
    public ResponseEntity<?>  saveCurrencyInformation(@RequestBody CurrencyExchangeRateModel request){
        boolean isvalidDate = isValidDate(request.getReportedOn());
        if(!isvalidDate){
            String msg = "Invalid date format value " + request.getReportedOn();
            throw new InvalidDateFormatException(msg);
        }
        currencyService.saveExchangeRate(request);
        return ResponseEntity.accepted().body(request);
    }

    @GetMapping(value = "/currency", produces = "application/json")
    // use request param so that from and to currencies are well defined.
    public ResponseEntity<?> getExchangeRate(@RequestParam(name = "from") String fromCurrency, @RequestParam(name = "to") String toCurrency)throws Exception{
         int rate = currencyService.getExchangeRate(fromCurrency, toCurrency);
        CurrencyExchangeRateModel result = new CurrencyExchangeRateModel();
        result.setFromCurrency(fromCurrency);
        result.setToCurrency(toCurrency);
        result.setExchangeRate(rate);
         if(rate == -1){
             String msg = "Exchange Rate not found for conversion from " + fromCurrency + "to " + toCurrency;
             throw new ExchangeRateNotFoundException(msg);

         }else{
             return ResponseEntity.accepted().body(result);
         }
    }

    public boolean isValidDate(String strDate){
        SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
        sdfrmt.setLenient(false);
        /* Create Date object
         * parse the string into date
         */
        try
        {
            Date javaDate = sdfrmt.parse(strDate);
            System.out.println(strDate+" is valid date format");
        }
        /* Date format is invalid */
        catch (ParseException e)
        {
            System.out.println(strDate+" is Invalid Date format");
            return false;
        }
        /* Return true if date format is valid */
        return true;
    }

}
