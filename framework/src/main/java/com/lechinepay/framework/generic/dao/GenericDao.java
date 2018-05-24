package com.lechinepay.framework.generic.dao;

public interface GenericDao<T, ID> {

    int deleteByPrimaryKey(ID pk);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(ID pk);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

}
