package cn.customer.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JdbcUtils {
    private  static final String DRIVERCLASS;
    private  static final String URL;
    private  static final String USERNAME;
    private  static final String PASSWORD;

    static{
        //ResourceBundle貌似是获取src下文件名为”jdbc”的文件
        DRIVERCLASS = ResourceBundle.getBundle("db").getString("driverClass");
        URL = ResourceBundle.getBundle("db").getString("url");
        USERNAME = ResourceBundle.getBundle("db").getString("username");
        PASSWORD = ResourceBundle.getBundle("db").getString("password");
    }
    static{
        try {
            Class.forName(DRIVERCLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        //获取连接
        Connection con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        return con;
    }
}

