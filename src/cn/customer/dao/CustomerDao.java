package cn.customer.dao;



import cn.customer.utils.DataSourceUtils;
import cn.customer.vo.Customer;
import cn.customer.vo.PageBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class CustomerDao {
    //查询所有用户
    public static List<Customer> findAll(int currentPage, int pageNum) throws SQLException {
        String sql="select * from customer limit ?,?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql,new BeanListHandler<Customer>(Customer.class),currentPage*pageNum,pageNum);
    }

    public static void delById(String id) throws SQLException {
        String sql="delete from customer where id=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        runner.update(sql,id);
    }

    public static Customer findById(String id) throws SQLException {
        String sql="select * from customer where id=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql,new BeanHandler<Customer>(Customer.class),id);
    }

    public static void update(Customer customer) throws SQLException {
        String sql="update customer set name=?,gender=?,birthday=?,cellphone=?,email=?,preference=?,description=? where id=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        runner.update(sql,customer.getName(),customer.getGender(),customer.getBirthday(),customer.getCellphone(),
                customer.getEmail(),customer.getPreference(),customer.getDescription(),customer.getId());
    }

    public static void addCus(Customer customer) throws SQLException {
        String select_sql="select count(*) from customer";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        long count = (long) runner.query(select_sql,new ScalarHandler());
        count++;
        String id = "a"+String.format("%0"+3+"d",(int)count);
        String sql="insert into customer values(?,?,?,?,?,?,?,?,?)";
        runner.update(sql,id,customer.getName(),customer.getGender(),customer.getBirthday(),customer.getCellphone(),
                customer.getEmail(),customer.getPreference(),customer.getType(),customer.getDescription());
    }

    public static void del_some(String[] ids) throws SQLException {
        String sql="delete from customer where id=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        Object[][] idss = new Object[ids.length][1];
        for(int i=0;i<ids.length;i++){
            idss[i][0] = ids[i];
        }
        runner.batch(sql,idss);
    }

    public static List<Customer> simpleSelect(String select_field, String msg) throws SQLException {
        String sql = null;
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        if(select_field.equals("")) {
            sql = "select * from customer";
            return runner.query(sql, new BeanListHandler<Customer>(Customer.class));
        }
        else {
            sql = "select * from customer where " + select_field + " like ?";
            return runner.query(sql, new BeanListHandler<Customer>(Customer.class), "%" + msg + "%");
        }
    }

    public static int allNum() throws SQLException {
        String sql="select count(*) from customer";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        long number = (long) runner.query(sql,new ScalarHandler());
        return (int)number;
    }

    public static int allPage(int pageNum) throws SQLException {
        return (int) Math.ceil(CustomerDao.allNum()*1.0/pageNum);
    }
}
