package com.baeldung.app;

import com.baeldung.rate.ExchangeRate;

public class Main {
    public static void main(String[] args) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.providers(true).forEach(provider -> {
            System.out.println(provider.create().getQuotes(null, null));
        });
    }
}
