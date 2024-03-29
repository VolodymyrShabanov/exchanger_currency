package repository;

import interfaces.model.ITransactionData;
import model.Account;
import model.AccountData;
import model.Currency;
import model.TransactionDepositData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryTest {
    Currency usd = new Currency("USD", "US Dollar");
    Currency eur = new Currency("EUR", "Euro");
    Currency pln = new Currency("PLN", "Polish Zloty");
    Currency cad = new Currency("CAD", "Canadian Dollar");
    Currency uah = new Currency("UAH", "Ukrainian Hryvnia");

    AccountData acc1 = new AccountData("a@gm.com", usd);
    AccountData acc2 = new AccountData("a@gm.com", eur);
    AccountData acc3 = new AccountData("alex@gm.com", pln);

    ITransactionData tr1 = new TransactionDepositData(acc1, 100);
    ITransactionData tr2 = new TransactionDepositData(acc2, 100);
    ITransactionData tr3 = new TransactionDepositData(acc2, 100);
    ITransactionData tr4 = new TransactionDepositData(acc3, 100);

    @Test
    void addTransaction() {
        TransactionRepository tr = new TransactionRepository();

        assertEquals(0, tr.getSize());

        tr.addTransaction(tr1);

        assertEquals(1, tr.getSize());

        tr.addTransaction(tr2);
        tr.addTransaction(tr3);

        assertEquals(1, tr.getSize());

        tr.addTransaction(tr4);

        assertEquals(2, tr.getSize());
    }

    @Test
    void getTransactionsByCurrency() {
        TransactionRepository tr = new TransactionRepository();

        assertFalse(tr.getTransactionsByCurrency(usd).isPresent());
        tr.addTransaction(tr1);
        assertTrue(tr.getTransactionsByCurrency(usd).isPresent());

        assertFalse(tr.getTransactionsByCurrency(pln).isPresent());
        tr.addTransaction(tr4);
        assertTrue(tr.getTransactionsByCurrency(pln).isPresent());

        assertFalse(tr.getTransactionsByCurrency(uah).isPresent());
        assertFalse(tr.getTransactionsByCurrency(cad).isPresent());
    }

    @Test
    void getTransactionsByUserEmail() {
        TransactionRepository tr = new TransactionRepository();

        assertFalse(tr.getTransactionsByUserEmail("kate@gm.ua").isPresent());

        assertFalse(tr.getTransactionsByUserEmail("a@gm.com").isPresent());
        tr.addTransaction(tr1);
        assertTrue(tr.getTransactionsByUserEmail("a@gm.com").isPresent());

        assertFalse(tr.getTransactionsByUserEmail("alex@gm.com").isPresent());
        tr.addTransaction(tr4);
        assertTrue(tr.getTransactionsByUserEmail("alex@gm.com").isPresent());
    }
}