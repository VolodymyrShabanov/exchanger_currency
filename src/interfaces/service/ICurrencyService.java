package interfaces.service;

import model.Account;
import model.Currency;
import model.ExchangeRate;
import model.TransactionExchangeData;

import java.util.Optional;

public interface ICurrencyService {

    Optional<TransactionExchangeData> exchangeCurrency(Account current, Account target, double amount);

    Optional<Currency> addCurrency(String code, String name);

    boolean deleteCurrency(Currency currencyToDelete);

    Optional<ExchangeRate> createExchangeRate(String fromCurrency, String toCurrency, double rate);

    Optional<ExchangeRate> updateExchangeRate(String currentCode, String targetCode, double newRate);

    Optional<Currency> getCurrencyByCode(String code);

    Optional<ExchangeRate> getExchangeRateByCode(String currentCode, String targetCode);
}
