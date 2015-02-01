package com.mars_crater.data.cache;

import com.mars_crater.dao.ExpenseTypeDAO;
import com.mars_crater.entities.ExpenseTypeEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by ateixeira on 31-01-2015.
 */
public class CacheManager {

    private ExpenseTypeCache expenseTypeCache = new ExpenseTypeCache();

    public CacheManager(EntityManager entityManager) {
        this.getExpenseTypeCache(entityManager);
    }

    private void getExpenseTypeCache(EntityManager entityManager) {
        final ExpenseTypeDAO expenseTypeDAO = new ExpenseTypeDAO();
        final List<ExpenseTypeEntity> allExpenseTypes = expenseTypeDAO.getAllExpenseTypes(entityManager);

        if (allExpenseTypes.isEmpty()) {
            System.out.println("SOMETHING IS WRONG!!!");
            return;
        }

        this.expenseTypeCache.addExpenseTypeList(allExpenseTypes);
    }

    public ExpenseTypeCache getExpenseTypeCache() {
        return this.expenseTypeCache;
    }
}
