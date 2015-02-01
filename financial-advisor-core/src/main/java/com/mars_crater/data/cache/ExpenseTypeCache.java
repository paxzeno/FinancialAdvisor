package com.mars_crater.data.cache;

import com.mars_crater.entities.ExpenseTypeEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ateixeira on 31-01-2015.
 */
public class ExpenseTypeCache {

    private Map<Integer, ExpenseTypeEntity> expenseTypeMap = new HashMap<>();

    public void addExpenseTypeList(List<ExpenseTypeEntity> expenseTypeList) {
        for (ExpenseTypeEntity expenseTypeEntity : expenseTypeList) {
            this.expenseTypeMap.put(expenseTypeEntity.getId(), expenseTypeEntity);
        }
    }

    public void addExpenseType(ExpenseTypeEntity expenseType) {
        this.expenseTypeMap.put(expenseType.getId(), expenseType);
    }

    public ExpenseTypeEntity getExpenseTypeById(Integer expenseTypeId) {
        return this.expenseTypeMap.get(expenseTypeId);
    }
}
