package cn.customer.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceUtils {
    private static ComboPooledDataSource cpds = new ComboPooledDataSource();
    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }
    public static DataSource getDataSource(){
        return cpds;
    }
}
