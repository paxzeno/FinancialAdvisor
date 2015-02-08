package com.mars_crater.data.sdk.model;

import com.mars_crater.BDUtils;
import com.mars_crater.data.cache.CacheManager;
import com.mars_crater.data.sdk.vo.ExpenseTypeVO;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import java.util.List;

/**
 * Created by ateixeira on 08-02-2015.
 */
public class ModelFacade {

    private static CacheManager CACHE_MANAGER;

    public ModelFacade() {
        try {
            CACHE_MANAGER = new CacheManager(BDUtils.getConnection());
        } catch (HeuristicRollbackException | HeuristicMixedException | NotSupportedException | RollbackException | SystemException e) {
            e.printStackTrace();
        }
    }

    public List<ExpenseTypeVO> getAllExpenseType() {
        return CACHE_MANAGER.getExpenseTypeCache().getAll();
    }
}
