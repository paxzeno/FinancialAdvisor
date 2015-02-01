package com.mars_crater.data.sdk;

import java.math.BigDecimal;

/**
 * Created by ateixeira on 18-01-2015.
 */
public class TransactionValue {

    private TransactionTypeEnum transactionType;

    private BigDecimal transactionValue;

    private CurrencyEnum currency;

    public TransactionValue(TransactionTypeEnum transactionType, BigDecimal transactionValue, CurrencyEnum currency) {
        this.transactionType = transactionType;
        this.transactionValue = transactionValue;
        this.currency = currency;
    }

    public TransactionTypeEnum getTransactionType() {
        return transactionType;
    }

    public BigDecimal getTransactionValue() {
        return transactionValue;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return transactionType + " " + transactionValue + currency;
    }
}
