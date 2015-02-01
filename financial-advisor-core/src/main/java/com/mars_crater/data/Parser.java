package com.mars_crater.data;

import com.mars_crater.BDUtils;
import com.mars_crater.data.cache.CacheManager;
import com.mars_crater.data.sdk.Balance;
import com.mars_crater.data.sdk.BalanceTypeEnum;
import com.mars_crater.data.sdk.BankExtract;
import com.mars_crater.data.sdk.CurrencyEnum;
import com.mars_crater.data.sdk.NBExtract;
import com.mars_crater.data.sdk.NBTransaction;
import com.mars_crater.data.sdk.TransactionTypeEnum;
import com.mars_crater.data.sdk.TransactionValue;
import com.mars_crater.entities.ExpenseTypeEntity;
import com.mars_crater.entities.TransactionEntity;
import com.mars_crater.entities.TransactionExpenseEntity;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;

/**
 * Parse raw data information from a copy paste from the website.
 *
 * Created by ateixeira on 18-01-2015.
 */
public class Parser {

    public static BankExtract bankExtract;

    public static void extractLine(String rawData) {
        try {
            final CacheManager cacheManager = new CacheManager(BDUtils.getConnection());
            final String[] split = rawData.split("\t");

            if (split.length > 1) {
                NBTransaction nbTransaction = new NBTransaction(split[0], split[1], split[2], createTransactionValue(split[3]), createBalanceValue(split[4]));
                bankExtract.put(nbTransaction);
                final TransactionEntity transactionEntity = nb2transaction(nbTransaction);
                final ExpenseTypeEntity expenseTypeEntity = cacheManager.getExpenseTypeCache().getExpenseTypeById(1);
                BDUtils.insertTransaction(transactionEntity);
                BDUtils.insertExpenseType(expenseTypeEntity);
                BDUtils.insertTrnsactionExpenseType(nb2transactionExpense(transactionEntity, expenseTypeEntity, nbTransaction));
            }
        } catch (NumberFormatException ex) {
            System.out.println("ops something went wrong!");
        } catch (Exception ex) {
            System.out.println("ops something went wrong! Generally speaking. like: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static TransactionExpenseEntity nb2transactionExpense(TransactionEntity transactionEntity, ExpenseTypeEntity expenseTypeEntity, NBTransaction nbTransaction) {
        final TransactionExpenseEntity transactionExpense = new TransactionExpenseEntity();
        transactionExpense.setTransaction(transactionEntity);
        transactionExpense.setExpenseType(expenseTypeEntity);
        transactionExpense.setTransactionDescription(nbTransaction.getTransactionDescription());
        return transactionExpense;
    }

    private static ExpenseTypeEntity nb2expense(NBTransaction nbTransaction) {
        final ExpenseTypeEntity expenseType = new ExpenseTypeEntity();
        expenseType.setSubType(1);
        expenseType.setExpenseDescription("Generic Expense");
        return expenseType;
    }

    private static TransactionEntity nb2transaction(NBTransaction nbTransaction) throws ParseException {
        final  SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        final TransactionEntity transaction = new TransactionEntity();
        transaction.setTransactionDate(formatter.parse(nbTransaction.getTransactionDate()));
        transaction.setEffectiveDate(formatter.parse(nbTransaction.getEfectiveDate()));
        transaction.setTransactionType(nbTransaction.getTransactionValue().getTransactionType().name());
        transaction.setTransactionValue(nbTransaction.getTransactionValue().getTransactionValue().toString());
        transaction.setTransactionCurrency(nbTransaction.getTransactionValue().getCurrency().name());
        transaction.setBalanceType(nbTransaction.getBalance().getBalanceType().name());
        transaction.setBalanceValue(nbTransaction.getBalance().getBalanceValue().toString());
        transaction.setBalanceCurrency(nbTransaction.getBalance().getCurrency().name());
        return transaction;
    }

    private static Balance createBalanceValue(String balance) {
        BalanceTypeEnum balanceType;
        CurrencyEnum curency;
        if (balance.matches("[+].*")) {
            balanceType = BalanceTypeEnum.POS;
        } else {
            balanceType = BalanceTypeEnum.NEG;
        }

        /*
           TODO: make a map to match currency.
         */

        return new Balance(balanceType, new BigDecimal(cleanNumberFormat(balance)), CurrencyEnum.EUR);
    }

    private static TransactionValue createTransactionValue(String transactionValue) {
        TransactionTypeEnum transactionType;
        CurrencyEnum curency;
        if (transactionValue.matches("[-dD].*")) {
            transactionType = TransactionTypeEnum.DEB;
        } else {
            transactionType = TransactionTypeEnum.CRD;
        }

        /*
           TODO: make a map to match currency.
         */

        return new TransactionValue(transactionType, new BigDecimal(cleanNumberFormat(transactionValue)), CurrencyEnum.EUR);
    }

    public static String cleanNumberFormat(String number) {
        String cleanedNumber = number.substring(2, number.length() - 2);
        return cleanedNumber.replaceAll("\\.", "").replace(",",".");
    }

    public static void main(String[] args) throws IOException, HeuristicRollbackException, HeuristicMixedException, NotSupportedException, RollbackException, SystemException {
        final Stream<String> nbFfileStream = Files.lines(Paths.get("../resources/rawData/nbCopyPaste.txt"));
        bankExtract = new NBExtract();
        BDUtils.createConnection();
        nbFfileStream.forEach(Parser::extractLine);
        BDUtils.closeConnection();
    }
}
