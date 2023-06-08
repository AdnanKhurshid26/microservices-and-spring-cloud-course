package com.adnan.microservices.currencyconversion;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {
    @Autowired
    private CurrencyExchangeProxy proxy;

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
            @PathVariable BigDecimal quantity) {

        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);

        if (currencyConversion == null) {
            throw new RuntimeException("Unable to find data for " + from + " to " + to);
        }
        return new CurrencyConversion(currencyConversion.getId(), from, to, currencyConversion.getConversionMultiple(),
                quantity, quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment());

    }

}
