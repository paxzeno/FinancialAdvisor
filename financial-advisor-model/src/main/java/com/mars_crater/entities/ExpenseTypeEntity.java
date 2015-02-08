package com.mars_crater.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Expense type categorize table.
 */
@Entity
@Table(name = "T_EXPENSE_TYPE")
@NamedQueries(
        @NamedQuery(name = ExpenseTypeEntity.GET_ALL_EXPENSE_TYPES, query = "SELECT e FROM ExpenseTypeEntity e")
)
public class ExpenseTypeEntity implements Serializable {

    private static final long serialVersionUID = -6410827024473603749L;

    public static final String GET_ALL_EXPENSE_TYPES = "getAllExpenseTypes";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXPENSE_TYPE_ID")
    private int id;

    @Column(name = "HERITAGE")
    private int heritage;

    @Column(name = "EXPENSE_TYPE_DESC")
    private String expenseDescription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeritage() {
        return heritage;
    }

    public void setHeritage(int heritage) {
        this.heritage = heritage;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }
}
