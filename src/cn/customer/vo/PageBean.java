package cn.customer.vo;

import java.util.List;

public class PageBean {
    private int currentPage;
    private int pageNum;
    private int allPageNum;
    private int allNum;
    private List<Customer> cs;

    public int getCurrentPage() {
        return currentPage;
    }


    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getAllPageNum() {
        return allPageNum;
    }

    public void setAllPageNum(int allPageNum) {
        this.allPageNum = allPageNum;
    }

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public List<Customer> getCs() {
        return cs;
    }

    public void setCs(List<Customer> cs) {
        this.cs = cs;
    }
}
