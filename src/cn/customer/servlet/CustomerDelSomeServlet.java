package cn.customer.servlet;

import cn.customer.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CustomerDelSomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //获取要删除的字段的id
        String[] ids = request.getParameterValues("ck");

        //创建一个对应的服务对象
        CustomerService service = new CustomerService();
        try {
            service.del_some(ids);
            // 跳转到CustomerFindAllServlet,就是要重新查询一次
            response.sendRedirect(request.getContextPath()+"/findAll");
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("批量删除数据失败");
            return;
        }
    }
}
