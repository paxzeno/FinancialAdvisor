package com.mars_crater;

import com.mars_crater.entities.ExpenseTypeEntity;
import com.mars_crater.entities.TagEntity;
import com.mars_crater.entities.TransactionEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

/**
 * BD Utils class.
 */
public final class BDUtils {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    public static void createConnection() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        emf = Persistence.createEntityManagerFactory("financial-advisor-unit");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    public static EntityManager getConnection() throws HeuristicRollbackException, HeuristicMixedException, NotSupportedException, RollbackException, SystemException {
        if (em == null) {
            createConnection();
        }
        return em;
    }

    public static void insertTransaction(TransactionEntity transaction) {
        em.persist(transaction);
    }

    public static void insertExpenseType(ExpenseTypeEntity expenseType) {
        em.persist(expenseType);
    }

    public static void insertTag(TagEntity tag) {
        em.persist(tag);
    }

    public static void closeConnection() {
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
