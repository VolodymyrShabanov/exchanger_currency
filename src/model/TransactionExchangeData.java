package model;

import interfaces.model.ITransactionData;
import util.CurrencyTransactionType;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionExchangeData implements ITransactionData {
    private final AccountData currentAccount;
    private final AccountData targetAccount;

    private final double conversionRate;

    private final double currentTransactionAmount;
    private final double targetTransactionAmount;

    private final CurrencyTransactionType currencyTransactionType;
    private final LocalDateTime transactionDate;

    public TransactionExchangeData(AccountData currentAccount,
                                   AccountData targetAccount,
                                   double conversionRate,
                                   double currentTransactionAmount,
                                   double targetTransactionAmount
    ) {
        this.currentAccount = currentAccount;
        this.targetAccount = targetAccount;

        this.conversionRate = conversionRate;

        this.currencyTransactionType = CurrencyTransactionType.EXCHANGE;
        this.currentTransactionAmount = currentTransactionAmount;
        this.targetTransactionAmount = targetTransactionAmount;

        transactionDate = LocalDate.now().atStartOfDay();
    }

    public AccountData getCurrentAccount() {
        return currentAccount;
    }

    public AccountData getTargetAccount() {
        return targetAccount;
    }

    public Currency getCurrentCurrency() {
        return currentAccount.getCurrency();
    }

    public Currency getTargetCurrency() {
        return targetAccount.getCurrency();
    }

    public double getCurrentTransactionAmount() {
        return currentTransactionAmount;
    }

    public String getFormattedCurrentAmount() {
        DecimalFormat df = new DecimalFormat("0.##");

        return df.format(currentTransactionAmount);
    }

    public double getTargetTransactionAmount() {
        return targetTransactionAmount;
    }

    public String getFormattedTargetAmount() {
        DecimalFormat df = new DecimalFormat("0.##");

        return df.format(targetTransactionAmount);
    }

    public CurrencyTransactionType getCurrencyTransactionType() {
        return currencyTransactionType;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public String getCurrent() {
        return currentAccount.toString();
    }

    public String getCurrentAmount() {
        DecimalFormat df = new DecimalFormat("0.##");
        return df.format(targetTransactionAmount);
    }

    @Override
    public String getInfo() {
        return String.format("\nFROM: %s %s\nTO: %s %s\n\n%s %s --> %s %s\n\nConversion Rate: %f\n",
                currentAccount.getUserEmail(),
                currentAccount.getCurrency().getCode(),
                targetAccount.getUserEmail(),
                targetAccount.getCurrency().getCode(),
                getFormattedCurrentAmount(),
                currentAccount.getCurrency().getCode(),
                getFormattedTargetAmount(),
                targetAccount.getCurrency().getCode(),
                conversionRate
        );
    }

    @Override
    public CurrencyTransactionType getType() {
        return currencyTransactionType;
    }

    @Override
    public String getDateInfo() {
        return transactionDate.toString();
    }

    @Override
    public String getUserEmail() {
        return currentAccount.getUserEmail();
    }

    @Override
    public List<AccountData> getAccounts() {
        return List.of(currentAccount, targetAccount);
    }

    @Override
    public List<Double> getAmounts() {
        return List.of(currentTransactionAmount, targetTransactionAmount);
    }

    @Override
    public List<Currency> getCurrencies() {
        return List.of(currentAccount.getCurrency(), targetAccount.getCurrency());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "currentAccount=" + currentAccount +
                ", targetAccount=" + targetAccount +
                ", conversionRate=" + conversionRate +
                ", currentTransactionAmount=" + currentTransactionAmount +
                ", targetTransactionAmount=" + targetTransactionAmount +
                ", currencyTransactionType=" + currencyTransactionType +
                ", transactionDate=" + transactionDate +
                '}';
    }
}