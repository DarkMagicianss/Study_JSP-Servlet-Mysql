package cn.customer.servlet;

import cn.customer.service.CustomerService;
import cn.customer.vo.Customer;
import cn.customer.vo.PageBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerFindAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        int currentPage = 0;
        String _currentPage = request.getParameter("currentPage");
        if(_currentPage!=null){
            currentPage = Integer.parseInt(_currentPage);
        }
        int pageNum=2;//人为设定为一页两条
        String _pageNum = request.getParameter("pageNum");
        if(_pageNum!=null){
            pageNum = Integer.parseInt(_pageNum);
        }

        CustomerService service = new CustomerService();
        try {
            //调用service的方法获取客户的信息
            PageBean pb = service.findAll(currentPage,pageNum);
            //将数据存在request域中
            request.setAttribute("pb",pb);
            request.getRequestDispatcher("/jsp/showCustomer.jsp").forward(request,response);
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("查询用户数据失败");
            return;
        }
    }
}
