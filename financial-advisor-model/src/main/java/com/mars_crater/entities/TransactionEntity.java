package com.mars_crater.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Transaction entity.
 */
@Entity
@Table(name = "T_TRANSACTION")
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = 8628316291006448518L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private int id;

    @Column(name = "TRANSACTION_DATE")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;

    @Column(name = "EFFECTIVE_DATE")
    @Temporal(TemporalType.DATE)
    private Date effectiveDate;

    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;

    @Column(name = "TRANSACTION_VALUE")
    private String transactionValue;

    @Column(name = "TRANSACTION_CURRENCY")
    private String transactionCurrency;

    @Column(name = "BALANCE_TYPE")
    private String balanceType;

    @Column(name = "BALANCE_VALUE")
    private String balanceValue;

    @Column(name = "BALANCE_CURRENCY")
    private String balanceCurrency;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(String transactionValue) {
        this.transactionValue = transactionValue;
    }

    public String getTransactionCurrency() {
        return transactionCurrency;
    }

    public void setTransactionCurrency(String transactionCurrency) {
        this.transactionCurrency = transactionCurrency;
    }

    public String getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }

    public String getBalanceValue() {
        return balanceValue;
    }

    public void setBalanceValue(String balanceValue) {
        this.balanceValue = balanceValue;
    }

    public String getBalanceCurrency() {
        return balanceCurrency;
    }

    public void setBalanceCurrency(String balanceCurrency) {
        this.balanceCurrency = balanceCurrency;
    }
}
