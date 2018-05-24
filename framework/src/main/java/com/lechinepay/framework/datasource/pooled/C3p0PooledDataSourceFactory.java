package com.lechinepay.framework.datasource.pooled;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class C3p0PooledDataSourceFactory extends PooledDataSourceFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(C3p0PooledDataSourceFactory.class);

    public C3p0PooledDataSourceFactory() {
        super();
        try {
            this.dataSource = (DataSource) Class.forName("com.mchange.v2.c3p0.ComboPooledDataSource").newInstance();
        } catch (InstantiationException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
