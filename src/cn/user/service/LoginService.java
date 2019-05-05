package cn.user.service;

import cn.user.dao.UserDao;
import cn.user.vo.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;

public class LoginService {
    public static User login(User user) throws SQLException {
        User select_user = null;
        select_user = UserDao.loginUser(user);
        return select_user;
    }
    public static Cookie rememberUser(User successUser) throws UnsupportedEncodingException {
        String username = successUser.getUsername();
        //先把中文的名称进行编码
        username = URLEncoder.encode(username,"UTF-8");
        String password = successUser.getPassword();
        String cookie_user = username+"-"+password;
        //解码
        //URLDecoder.decode(username,"UTF-8");
        //记住用户名,保存到cookie中
        Cookie cookie = new Cookie("log_user",cookie_user);
        //设置有效时间
        cookie.setMaxAge(60*60);
        //设置有效路径 使得整个服务都能获取cookie
        cookie.setPath("/");
        return cookie;
    }
    public static Cookie clearRememberUser(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie:cookies){
                //查找对应的username cookie
                if(cookie.getName().equals("log_user")){
                    //取消对应cookie内容的保存
                    cookie.setMaxAge(0);
                    //设置有效路径 使得整个服务都能获取cookie
                    cookie.setPath("/");
                    return cookie;
                }
            }
        }
        return null;
    }
}
