package cn.user.service;

import cn.user.dao.UserDao;
import cn.user.vo.User;

import java.sql.SQLException;


//操作XML的
public class RegService {
    public static final int SUCCESS = 0;
    public static final int NAMEEXIST = 1;
    public static final int EMAILEXIST = 2;

    public static final int REGSUCCESS = 1;
    public static final int REGFAIL = 0;

    //用户名不能重复,邮箱是否重名
    public int checkUser(User user) throws SQLException {
        //使用user对象查询mysql中是否具有相同用户名的信息
        User select_user = UserDao.checkUser(user);
        if(select_user != null){
            if(select_user.getUsername().equals(user.getUsername())){
                //有查到结果用户名相同
                return RegService.NAMEEXIST;
            }else{
                //有查到结果不是用户名相同就是邮箱相同
                return RegService.EMAILEXIST;
            }
        }else{
            return RegService.SUCCESS;
        }
    }
    //注册用户信息
    public int regUser(User user) throws SQLException {
        //使用user对象进行mysql数据信息的添加
        int result = UserDao.regUser(user);
        if(result == 0){
            return RegService.REGFAIL;
        }else {
            return RegService.REGSUCCESS;
        }
    }

}
