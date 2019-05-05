package cn.customer.service;

import cn.customer.dao.CustomerDao;
import cn.customer.vo.Customer;
import cn.customer.vo.PageBean;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {

    public PageBean findAll(int currentPage,int pageNum) throws SQLException {
        //调用Dao的方法获取客户信息
        PageBean pb = new PageBean();
        List<Customer> cs = CustomerDao.findAll(currentPage,pageNum);
        pb.setCs(cs);
        pb.setCurrentPage(currentPage);
        pb.setPageNum(pageNum);
        pb.setAllNum(CustomerDao.allNum());
        pb.setAllPageNum(CustomerDao.allPage(pageNum));
        return pb;
    }

    public void delById(String id) throws SQLException {
        //调用Dao的方法删除客户信息
        CustomerDao.delById(id);
    }

    public Customer findById(String id) throws SQLException {
        //调用Dao的方法查询客户信息
        return CustomerDao.findById(id);
    }

    public void update(Customer customer) throws SQLException {
        //调用Dao的方法修改客户信息
        CustomerDao.update(customer);
    }

    public void addCus(Customer customer) throws SQLException {
        //调用Dao的方法添加客户信息
        CustomerDao.addCus(customer);
    }

    public void del_some(String[] ids) throws SQLException {
        //调用Dao的方法批量删除客户信息
        CustomerDao.del_some(ids);
    }

    public List<Customer> simpleSelect(String select_field, String msg) throws SQLException {
        //调用Dao的方法简单查询客户信息
        return CustomerDao.simpleSelect(select_field,msg);
    }
}
