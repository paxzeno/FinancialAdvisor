package com.mars_crater.data.cache;

import com.mars_crater.data.sdk.vo.ExpenseTypeVO;
import com.mars_crater.entities.ExpenseTypeEntity;

import java.util.ArrayList;
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

    public List<ExpenseTypeVO> getAll() {
        List<ExpenseTypeVO> expenseTypeList = new ArrayList<>(expenseTypeMap.values().size());
        for (ExpenseTypeEntity expenseTypeEntity : expenseTypeMap.values()) {
            final ExpenseTypeVO expenseType = new ExpenseTypeVO();
            expenseType.setId(expenseTypeEntity.getId());
            expenseType.setHeritage(expenseTypeEntity.getHeritage());
            expenseType.setExpenseDescription(expenseTypeEntity.getExpenseDescription());
            expenseTypeList.add(expenseType);
        }
        return expenseTypeList;
    }
}
