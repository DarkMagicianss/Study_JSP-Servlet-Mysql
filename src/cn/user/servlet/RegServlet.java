package cn.user.servlet;

import cn.user.service.RegService;
import cn.user.vo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Map;

//完成注册
public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数列表
        //2.需要把数据封装到JavaBean中,使用BeanUtils开发包
        //3.处理数据,调用另一个JavaBean
        //4.把结果返回到页面中

        //获取参数列表
        request.setCharacterEncoding("UTF-8");
        //获取请求头 必须从注册界面过来的请求才可以
        /*
        String referer = request.getHeader("referer");
        if(referer == null || !(referer.endsWith("reg.jsp"))){
            //获取虚拟路径
            //request获取客户机的信息(也可以转发)
            response.sendRedirect(request.getContextPath()+"/jsp/reg.jsp");//加项目名！！！
            return;
        }
        */
        Map<String,String[]> map = request.getParameterMap();
        User user = new User();
        //封装数据
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //处理
        RegService reg = new RegService();
        int flag = 0;
        try {
            flag = reg.checkUser(user);
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        //添加登入失败自动填写用户数据    主要用于登入失败后填充数据
        String req_username = URLEncoder.encode(user.getUsername(),"UTF-8");
        String req_password = user.getPassword();
        String req_nickname = URLEncoder.encode(user.getNickname(),"UTF-8");
        String req_email = user.getEmail();
        String req_user = req_username+"-"+req_password+"-"+req_nickname+"-"+req_email;
        request.setAttribute("req_user",req_user);

        if(flag == RegService.NAMEEXIST){
            //用户名重名了
            request.setAttribute("username_error","用户名重复!");
            //在服务器内部转发回注册页面 不写项目名
            request.getRequestDispatcher("/jsp/reg.jsp").forward(request,response);
        }else if(flag == RegService.EMAILEXIST){
            //邮箱重名了
            request.setAttribute("email_error","邮箱重复!");
            //在服务器内部转发回注册页面 不写项目名
            request.getRequestDispatcher("/jsp/reg.jsp").forward(request,response);
        }else if (flag == RegService.SUCCESS){
            //判断验证码是否正确
            //获取request提交的验证码
            String checkcode_post = request.getParameter("checkcode");//map.get("checkcode")[0];
            //拿到CheckServlet存入Session的真实的验证码
            String checkcode_true = (String) request.getSession().getAttribute("checkcode");
            if(checkcode_post.equalsIgnoreCase(checkcode_true)) {
                //将用户数据写入mysql数据库
                try {
                    int reg_result = reg.regUser(user);
                    if(reg_result == RegService.REGFAIL){
                        response.getWriter().write("注册失败！");
                        return;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //注册成功
                request.setAttribute("username_error", "");
                request.setAttribute("email_error", "");
                request.setAttribute("checkcode_error","");
                //应该要进行重定向 改变地址栏的url 并不需要进行request对象数据的传输
                response.sendRedirect("jsp/login.jsp");
            }else {
                //验证码错误
                request.setAttribute("checkcode_error","验证码错误!");
                //在服务器内部转发回注册页面 不写项目名
                request.getRequestDispatcher("/jsp/reg.jsp").forward(request,response);
            }
        }else{
            response.getWriter().write("ERROR!!!");
        }
    }
}
