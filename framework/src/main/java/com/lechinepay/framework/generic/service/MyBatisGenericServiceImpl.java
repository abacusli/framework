package com.lechinepay.framework.generic.service;

import com.lechinepay.framework.generic.dao.GenericDao;
import com.lechinepay.framework.transaction.Transaction;

public class MyBatisGenericServiceImpl<T, ID> implements MyBatisGenericService<T, ID> {

    protected GenericDao<T, ID> genericDao;

    protected Transaction       transaction;

    public GenericDao<T, ID> getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(GenericDao<T, ID> genericDao) {
        this.genericDao = genericDao;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public int deleteByPrimaryKey(ID pk) {
        try {
            int result = genericDao.deleteByPrimaryKey(pk);
            transaction.commit();
            return result;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        } finally {
            transaction.close();
        }
    }

    @Override
    public int insert(T record) {
        try {
            int result = genericDao.insert(record);
            transaction.commit();
            return result;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        } finally {
            transaction.close();
        }
    }

    @Override
    public int insertSelective(T record) {
        try {
            int result = genericDao.insertSelective(record);
            transaction.commit();
            return result;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        } finally {
            transaction.close();
        }
    }

    @Override
    public T selectByPrimaryKey(ID pk) {
        try {
            T result = genericDao.selectByPrimaryKey(pk);
            transaction.commit();
            return result;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        } finally {
            transaction.close();
        }
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        try {
            int result = genericDao.updateByPrimaryKeySelective(record);
            transaction.commit();
            return result;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        } finally {
            transaction.close();
        }
    }

    @Override
    public int updateByPrimaryKey(T record) {
        try {
            int result = genericDao.updateByPrimaryKey(record);
            transaction.commit();
            return result;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        } finally {
            transaction.close();
        }
    }
}
