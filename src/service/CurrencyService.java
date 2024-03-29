package service;


import exceptions.DataInUseException;
import exceptions.DataInitializationException;
import exceptions.DataNotFoundException;
import exceptions.TransactionException;
import interfaces.service.ICurrencyService;
import model.*;
import repository.CurrencyRepository;
import repository.ExchangeRateRepository;

import java.util.Optional;

public class CurrencyService implements ICurrencyService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final CurrencyRepository currencyRepository;

    private final boolean dataInitStatus;

    public CurrencyService() {
        this.currencyRepository = new CurrencyRepository();
        this.exchangeRateRepository = new ExchangeRateRepository();

        try {
            init();
            dataInitStatus = true;
        } catch (DataInitializationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TransactionExchangeData exchangeCurrency(AccountData current, AccountData target, double amount)
            throws DataNotFoundException, TransactionException {
        Optional<ExchangeRate> exchangeRate = exchangeRateRepository.getExchangeRate(
                current.getCurrency(),
                target.getCurrency()
        );

        if (amount < 1) {
            throw new TransactionException("Error: invalid exchange sum.");
        }

        if (exchangeRate.isEmpty()) {
            throw new DataNotFoundException("Error: this exchange rate doesn't exist.");
        }

        double exchangedSum = amount * exchangeRate.get().getRate();

        return new TransactionExchangeData(
                current,
                target,
                exchangeRate.get().getRate(),
                amount,
                exchangedSum
        );
    }

    @Override
    public Currency addCurrency(String currencyCode, String currencyName) throws IllegalArgumentException, DataInUseException {
        if (currencyRepository.getCurrencyByCode(currencyCode).isPresent() ||
                currencyRepository.getCurrencyByName(currencyName).isPresent()) {
            throw new DataInUseException("Error: currencies with similar data already exist.");
        }

        Optional<Currency> currencyOptional = currencyRepository.addCurrency(currencyCode, currencyName);

        if (currencyOptional.isEmpty()) {
            throw new IllegalArgumentException("Error: invalid currency data provided.");
        }

        return currencyOptional.get();
    }

    @Override
    public void deleteCurrency(Currency currencyToDelete, boolean currencyUsed) throws DataInUseException {
        if (currencyUsed) {
            throw new DataInUseException("Error: can't delete the currency in use.");
        }

        currencyRepository.deleteCurrencyByCode(currencyToDelete.getCode());
        System.out.printf("- Currency '%s' has successfully been deleted.\n", currencyToDelete.getName());
    }

    @Override
    public void createExchangeRate(String fromCurrency, String toCurrency, double rate)
            throws DataNotFoundException, DataInUseException {
        Optional<Currency> sourceCurrency = currencyRepository.getCurrencyByCode(fromCurrency);
        Optional<Currency> targetCurrency = currencyRepository.getCurrencyByCode(toCurrency);

        if (sourceCurrency.isEmpty() || targetCurrency.isEmpty()) {
            throw new DataNotFoundException("Error: one of the currencies doesn't exist.");
        }

        Optional<ExchangeRate> exchangeRate = exchangeRateRepository.getExchangeRate(sourceCurrency.get(), targetCurrency.get());

        if (exchangeRate.isPresent()) {
            throw new DataInUseException("Error: this rate already exists.");
        }

        ExchangeRate newExchangeRate = new ExchangeRate(sourceCurrency.get(), targetCurrency.get(), rate);
        exchangeRateRepository.addExchangeRate(newExchangeRate);
    }

    @Override
    public void updateExchangeRate(String currentCode, String targetCode, double newRate) throws DataNotFoundException {
        Optional<Currency> sourceCurrency = currencyRepository.getCurrencyByCode(currentCode);
        Optional<Currency> targetCurrency = currencyRepository.getCurrencyByCode(targetCode);

        if (sourceCurrency.isEmpty() || targetCurrency.isEmpty()) {
            throw new DataNotFoundException("Error: one of the currencies doesn't exist.");
        }

        Optional<ExchangeRate> exchangeRate = exchangeRateRepository.getExchangeRate(sourceCurrency.get(), targetCurrency.get());

        if (exchangeRate.isEmpty()) {
            throw new DataNotFoundException("Error: this rate doesn't exist.");
        }

        exchangeRate.get().setRate(newRate);
        System.out.printf("- Exchange rate (%s --> %s) has successfully been updated.\n", currentCode, targetCode);
    }

    @Override
    public Currency getCurrencyByCode(String code) throws DataNotFoundException {
        Optional<Currency> currencyOptional = currencyRepository.getCurrencyByCode(code);

        if (currencyOptional.isEmpty()) {
            throw new DataNotFoundException("Error: currency with this code doesn't exist.");
        }

        return currencyOptional.get();
    }

    @Override
    public ExchangeRate getExchangeRateByCode(String currentCode, String targetCode) throws DataNotFoundException {
        Optional<Currency> current = currencyRepository.getCurrencyByCode(currentCode);
        Optional<Currency> target = currencyRepository.getCurrencyByCode(targetCode);

        if (current.isEmpty() || target.isEmpty()) {
            throw new DataNotFoundException("Error: one of the currencies doesn't exist.");
        }

        Optional<ExchangeRate> exchangeRate = exchangeRateRepository.getExchangeRate(current.get(), target.get());

        if (exchangeRate.isEmpty()) {
            throw new DataNotFoundException("Error: this exchange rate doesn't exist.");
        }

        return exchangeRate.get();
    }

    private void init() throws DataInitializationException {
        if (dataInitStatus) {
            throw new DataInitializationException("Error: repo has already been initialized.");
        }

        try {
            addCurrency("USD", "US Dollar");
            addCurrency("EUR", "Euro");
            addCurrency("PLN", "Polish Zloty");

            createExchangeRate("EUR", "USD", 1.1);
            createExchangeRate("USD", "EUR", 0.9);
            createExchangeRate("USD", "PLN", 4);
            createExchangeRate("PLN", "USD", 0.25);
            createExchangeRate("EUR", "PLN", 4.3);
            createExchangeRate("PLN", "EUR", 0.23);
        } catch (DataInUseException e) {
            throw new RuntimeException(e);
        }
    }
}