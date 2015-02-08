package com.mars_crater.data;

import com.mars_crater.BDUtils;
import com.mars_crater.data.cache.CacheManager;
import com.mars_crater.data.sdk.entities.Balance;
import com.mars_crater.data.sdk.entities.BalanceTypeEnum;
import com.mars_crater.data.sdk.entities.BankExtract;
import com.mars_crater.data.sdk.entities.CurrencyEnum;
import com.mars_crater.data.sdk.entities.NBExtract;
import com.mars_crater.data.sdk.entities.NBTransaction;
import com.mars_crater.data.sdk.entities.TransactionTypeEnum;
import com.mars_crater.data.sdk.entities.TransactionValue;
import com.mars_crater.data.utils.TagMaker;
import com.mars_crater.entities.ExpenseTypeEntity;
import com.mars_crater.entities.TransactionEntity;

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

    public static BankExtract BANK_EXTRACT;

    public static CacheManager CACHE_MANAGER;

    //TODO Possibly go to cache manager in the future.
//    public static TagMaker TAG_MAKER;

    public static void extractLine(String rawData) {
        try {
            final String[] split = rawData.split("\t");

            if (split.length > 1) {
                NBTransaction nbTransaction = new NBTransaction(split[0], split[1], split[2], createTransactionValue(split[3]), createBalanceValue(split[4]));
                BANK_EXTRACT.put(nbTransaction);
                final TransactionEntity transactionEntity = nb2transaction(nbTransaction);
                final ExpenseTypeEntity expenseTypeEntity = CACHE_MANAGER.getExpenseTypeCache().getExpenseTypeById(1);
                BDUtils.insertTransaction(transactionEntity);
//                BDUtils.insertExpenseType(nb2expense(nbTransaction));

                //TODO: TEST DRIVE SOLUTION
                TagMaker.lookingForTags(CACHE_MANAGER.getTagCache().getCacheMap(), nbTransaction.getTransactionDescription());
            }
        } catch (NumberFormatException ex) {
            System.out.println("ops something went wrong!");
        } catch (Exception ex) {
            System.out.println("ops something went wrong! Generally speaking. like: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static ExpenseTypeEntity nb2expense(NBTransaction nbTransaction) {
        final ExpenseTypeEntity expenseType = new ExpenseTypeEntity();
        expenseType.setHeritage(1);
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
        BDUtils.createConnection();
        BANK_EXTRACT = new NBExtract();
        CACHE_MANAGER = new CacheManager(BDUtils.getConnection());
        nbFfileStream.forEach(Parser::extractLine);
        BDUtils.closeConnection();
    }
}
