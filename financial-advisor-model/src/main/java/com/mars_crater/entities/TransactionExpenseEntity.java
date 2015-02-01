package com.mars_crater.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * Relates the transaction with the expense table.
 */
@Entity
@Table(name = "T_TRANSACTION_EXPENSE")
public class TransactionExpenseEntity {

    @MapsId("id")
    @ManyToOne
    private TransactionEntity transaction;

    @MapsId("id")
    @ManyToOne
    private ExpenseTypeEntity expenseType;

    @Column(name = "TRANSACTION_DESC")
    private String transactionDescription;

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transactionId) {
        this.transaction = transactionId;
    }

    public ExpenseTypeEntity getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseTypeEntity expenseType) {
        this.expenseType = expenseType;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }
}
