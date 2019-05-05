package cn.user.servlet;

import cn.user.service.LoginService;
import cn.user.vo.User;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //获取参数列表
        Map<String,String[]> map = request.getParameterMap();
        //创建User对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        User successUser = null;
        try {
            successUser = LoginService.login(user);
        }catch (SQLException e) {
            e.printStackTrace();
        }

        if(successUser != null){
            //完成记住用户名的功能
            //获取复选框的内容
            String remember = request.getParameter("remember");
            if(remember != null && "remember".equals(remember)){
                //将用户信息记录到cookie中
                Cookie rem_cookie = LoginService.rememberUser(successUser);
                //回写cookie
                response.addCookie(rem_cookie);
            }else{
                //清除记住用户名的cookie
                Cookie cle_cookie = LoginService.clearRememberUser(request);
                //回写cookie
                if(cle_cookie!=null)
                    response.addCookie(cle_cookie);
            }
            //存储用户信息，重定向到新页面
            HttpSession session = request.getSession();
            session.setAttribute("success_user",successUser);
            response.sendRedirect("/myServlet/jsp/success.jsp");
        }else{
            //此时登入用户的用户名和密码可能和数据库中的不一致
            //因此清楚记住用户名的cookie
            Cookie cle_cookie = LoginService.clearRememberUser(request);
            //回写cookie
            if(cle_cookie!=null)
                response.addCookie(cle_cookie);
            request.setAttribute("login_error","用户名或者密码错误");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request,response);
        }
    }
}
