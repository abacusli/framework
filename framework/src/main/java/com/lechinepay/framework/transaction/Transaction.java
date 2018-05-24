package com.lechinepay.framework.transaction;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSession;

public class Transaction {

    private final SqlSession sqlSession;

    public Transaction(SqlSession sqlSession) {
        super();
        this.sqlSession = sqlSession;
    }

    public Connection getConnection() {
        return sqlSession.getConnection();
    }

    public void commit() {
        sqlSession.commit();
    }

    public void rollback() {
        sqlSession.rollback();
    }

    public void close() {
        sqlSession.close();
    }

    public static Transaction newTransaction(SqlSession sqlSession) {
        return new Transaction(sqlSession);
    }

}
