package com.baeldung.rate;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.stream.Collectors;

import com.baeldung.rate.spi.ExchangeRateProvider;

public class ExchangeRate {

    public Iterable<ExchangeRateProvider> providers(boolean refresh) {
        ServiceLoader<ExchangeRateProvider> loader = ServiceLoader.load(ExchangeRateProvider.class);
        if (refresh) {
            loader.reload();
        }
        return loader.stream().map(Provider::get).collect(Collectors.toList());
    }
}
