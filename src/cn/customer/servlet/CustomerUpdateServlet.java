package cn.customer.servlet;

import cn.customer.service.CustomerService;
import cn.customer.utils.MyDateConvert;
import cn.customer.vo.Customer;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import java.util.Date;
import java.util.Map;

public class CustomerUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取发送过来的用户信息
        Map<String,String> map =request.getParameterMap();
        Customer customer = new Customer();
        //进行BeanUtils的Date类型的转换
        try {
            ConvertUtils.register(new MyDateConvert(), Date.class);
            BeanUtils.populate(customer,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        CustomerService service = new CustomerService();
        try {
            service.update(customer);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("修改失败");
            return;
        }
        // 跳转到CustomerFindAllServlet,就是要重新查询一次
        response.sendRedirect(request.getContextPath()+"/findAll");
        return;
    }
}
