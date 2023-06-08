package com.adnan.microservices.currencyconversion;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="currency-exchange",url = "localhost:8000")
public interface CurrencyExchangeProxy {
    
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(String from, String to);
    
}
