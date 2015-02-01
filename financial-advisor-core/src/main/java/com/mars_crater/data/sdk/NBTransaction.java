package com.mars_crater.data.sdk;

import java.math.BigDecimal;

/**
 * Created by ateixeira on 18-01-2015.
 */
public class NBTransaction implements Transaction {

    private String transactionDate;

    private String efectiveDate;

    private String transactionDescription;

    private TransactionValue transactionValue;

    private Balance balance;

    public NBTransaction(String transactionDate, String efectiveDate, String transactionDescription, TransactionValue transactionValue, Balance balance) {
        this.transactionDate = transactionDate;
        this.efectiveDate = efectiveDate;
        this.transactionDescription = transactionDescription;
        this.transactionValue = transactionValue;
        this.balance = balance;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getEfectiveDate() {
        return efectiveDate;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public TransactionValue getTransactionValue() {
        return transactionValue;
    }

    public Balance getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "NBTransaction{" +
                "transactionDate='" + transactionDate + '\'' +
                ", efectiveDate='" + efectiveDate + '\'' +
                ", transactionDescription='" + transactionDescription + '\'' +
                ", transactionValue=" + transactionValue +
                ", balance=" + balance +
                '}';
    }
}
