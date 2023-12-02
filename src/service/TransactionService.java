package service;

import interfaces.ITransactionData;
import model.Currency;
import repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Volodymyr Sh on 28.11.2023
 * project name: exchanger_currency
 */
public class TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionService() {
        this.transactionRepository = new TransactionRepository();
    }

    public void addNewTransaction(ITransactionData transactionData) {
        transactionRepository.addTransaction(transactionData);
    }

    public boolean displayTransactionsByUserEmail(String userEmail) {
        Optional<List<ITransactionData>> dataList = transactionRepository.getTransactionsByUserEmail(userEmail);

        if (dataList.isEmpty()) return false;

        System.out.printf("User %s transaction history:\n", dataList.get().get(0).getUserEmail());

        dataList.get().forEach(dataEl -> {
            System.out.println(dataEl.getInfo());
        });

        return true;
    }

    public boolean displayTransactionsByCurrency(Currency currency) {
        Optional<List<ITransactionData>> dataList = transactionRepository.getTransactionsByCurrency(currency);

        if (dataList.isEmpty()) return false;

        System.out.printf("%s currency transaction history:\n", currency.getCode());

        dataList.get().forEach(dataEl -> {
            System.out.println(dataEl.getInfo());
        });

        return true;
    }
}