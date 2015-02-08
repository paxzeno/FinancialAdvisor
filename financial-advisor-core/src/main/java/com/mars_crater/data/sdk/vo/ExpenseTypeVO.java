package com.mars_crater.data.sdk.vo;

/**
 * Expense Type Value Object.
 */
public class ExpenseTypeVO {

    private int id;

    private int heritage;

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

    @Override
    public String toString() {
        return "ExpenseTypeVO{" +
                "id=" + id +
                ", heritage=" + heritage +
                ", expenseDescription='" + expenseDescription + '\'' +
                '}';
    }
}
