package com.mars_crater.data.cache;

import com.mars_crater.dao.ExpenseTypeDAO;
import com.mars_crater.dao.TagDAO;
import com.mars_crater.entities.ExpenseTypeEntity;
import com.mars_crater.entities.TagEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by ateixeira on 31-01-2015.
 */
public class CacheManager {

    private ExpenseTypeCache expenseTypeCache = new ExpenseTypeCache();

    private TagCache tagCache = new TagCache();

    public CacheManager(EntityManager entityManager) {
        this.getExpenseTypeCache(entityManager);
        this.getTagCache(entityManager);
    }

    private void getTagCache(EntityManager entityManager) {
        final TagDAO tagDAO = new TagDAO();
        final List<TagEntity> allTags = tagDAO.getAllTags(entityManager);

        if (allTags.isEmpty()) {
            System.out.println("No tags found");
        }

        this.tagCache.addEntityList(allTags);
    }

    public TagCache getTagCache() {
        return tagCache;
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
