package cn.customer.servlet;

import cn.customer.service.CustomerService;
import cn.customer.vo.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CustomerFindByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //获取要编辑的id信息
        String id = request.getParameter("id");
        //获取对应的操作客户信息的service
        CustomerService service = new CustomerService();
        try {
            Customer customer = service.findById(id);
            request.setAttribute("c",customer);
            request.getRequestDispatcher("/jsp/customerInfo.jsp").forward(request,response);
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("编辑用户数据失败");
            return;
        }
    }
}
