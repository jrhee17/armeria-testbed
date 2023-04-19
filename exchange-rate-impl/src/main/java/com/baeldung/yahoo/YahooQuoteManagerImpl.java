package com.baeldung.yahoo;

import java.time.LocalDate;
import java.util.List;

import com.baeldung.rate.api.Quote;
import com.baeldung.rate.api.QuoteManager;

public class YahooQuoteManagerImpl implements QuoteManager {

    @Override
    public List<Quote> getQuotes(String baseCurrency, LocalDate date) {
        // fetch from Yahoo API
        return List.of(new Quote());
    }
}
