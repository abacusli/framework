package com.lechinepay.framework.generic.dao;

import org.apache.ibatis.session.SqlSession;

public class MyBatisGenericDaoImpl<T, ID> implements MyBatisGenericDao<T, ID> {

    private Class<? extends MyBatisGenericDao<T, ID>> mapperType;

    public Class<? extends MyBatisGenericDao<T, ID>> getMapperType() {
        return mapperType;
    }

    public void setMapperType(Class<? extends MyBatisGenericDao<T, ID>> mapperType) {
        this.mapperType = mapperType;
    }

    private SqlSession sqlSession;

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public int deleteByPrimaryKey(ID pk) {
        return sqlSession.getMapper(mapperType).deleteByPrimaryKey(pk);
    }

    @Override
    public int insert(T record) {
        return sqlSession.getMapper(mapperType).insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return sqlSession.getMapper(mapperType).insertSelective(record);
    }

    @Override
    public T selectByPrimaryKey(ID pk) {
        return sqlSession.getMapper(mapperType).selectByPrimaryKey(pk);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return sqlSession.getMapper(mapperType).updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return sqlSession.getMapper(mapperType).updateByPrimaryKey(record);
    }

}
