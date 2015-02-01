package com.mars_crater.dao;

import com.mars_crater.entities.ExpenseTypeEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Expenses type DAO.
 */
public class ExpenseTypeDAO {

    public List<ExpenseTypeEntity> getAllExpenseTypes(EntityManager entityManager) {
        final TypedQuery getAllExpenseTypes = entityManager.createNamedQuery(ExpenseTypeEntity.GET_ALL_EXPENSE_TYPES, ExpenseTypeEntity.class);
        return getAllExpenseTypes.getResultList();
    }
}
