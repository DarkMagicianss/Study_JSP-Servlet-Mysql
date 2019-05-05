package cn.user.dao;

import cn.user.utils.JdbcUtils;
import cn.user.vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public static User checkUser(User user) throws SQLException {
        //sql语句
        String sql = "select * from user where username=? or email=? ";
        //获取连接器
        Connection con = JdbcUtils.getConnection();
        //通过连接器获取操作sql语句的Statement对象
        PreparedStatement pst = con.prepareStatement(sql);
        //设置对应的占位符的内容
        pst.setString(1,user.getUsername());
        pst.setString(2,user.getEmail());
        //执行对应的sql语句
        ResultSet rs =  pst.executeQuery();
        //创建一个用于返回查询信息的User对象
        User select_user = null;
        if(rs.next()){
            select_user = new User();
            select_user.setUsername(rs.getString("username"));
            select_user.setPassword(rs.getString("password"));
            select_user.setNickname(rs.getString("nickname"));
            select_user.setEmail(rs.getString("email"));
        }
        //释放资源
        rs.close();
        pst.close();
        con.close();
        return select_user;
    }
    public static int regUser(User user) throws SQLException {
        //sql语句
        String sql = "insert into user values (NULL,?,?,?,?)";
        //获取连接器
        Connection con = JdbcUtils.getConnection();
        //通过连接器获取操作sql语句的Statement对象
        PreparedStatement pst = con.prepareStatement(sql);
        //设置对应的占位符的内容
        pst.setString(1,user.getUsername());
        pst.setString(2,user.getPassword());
        pst.setString(3,user.getNickname());
        pst.setString(4,user.getEmail());
        //执行对应的sql语句 将修改的数值返回
        int result = pst.executeUpdate();
        //释放资源
        pst.close();
        con.close();
        return result;
    }
    public static User loginUser(User user) throws SQLException {
        //sql语句
        String sql = "select * from user where username=? and password=?";
        //获取连接器
        Connection con = JdbcUtils.getConnection();
        //通过连接器获取操作sql语句的Statement对象
        PreparedStatement pst = con.prepareStatement(sql);
        //设置对应的占位符的内容
        pst.setString(1,user.getUsername());
        pst.setString(2,user.getPassword());
        //执行对应的sql语句 将修改的数值返回
        ResultSet rs =  pst.executeQuery();
        //创建一个用于返回查询信息的User对象
        User select_user = null;
        if(rs.next()){
            select_user = new User();
            select_user.setUsername(rs.getString("username"));
            select_user.setPassword(rs.getString("password"));
            select_user.setNickname(rs.getString("nickname"));
            select_user.setEmail(rs.getString("email"));
        }
        //释放资源
        pst.close();
        con.close();
        return select_user;
    }
}
