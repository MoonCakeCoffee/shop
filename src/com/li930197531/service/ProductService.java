package com.li930197531.service;

import com.li930197531.dao.ProductDao;
import com.li930197531.domain.Category;
import com.li930197531.domain.PageBean;
import com.li930197531.domain.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    public List<Product> findHotProductList() {
        ProductDao dao = new ProductDao();
        List<Product> hotProductList = null;
        try {
            hotProductList = dao.findHotProductList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotProductList;
    }

    public List<Product> findNewProductList() {
        ProductDao dao = new ProductDao();
        List<Product> newProductList = null;
        try {
            newProductList = dao.findNewxProductList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newProductList;
    }

    public List<Category> findAllCategory() {
        ProductDao dao = new ProductDao();
        List<Category> categoryList = null;
        try {
            categoryList = dao.findAllCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    //封装一个pageBean 返回web层
    public PageBean findProductListByCid(String cid,int currentPage,int currentCount) {
        ProductDao dao = new ProductDao();
        PageBean<Product> pageBean = new PageBean<Product>();
        //1.封装当前页

        pageBean.setCurrentPage(currentPage);
        //2.封装每页显示的条数
        pageBean.setCurrentCount(currentCount);
        //3.封装总条数
        int totalCount = 0;
        try {
            totalCount = dao.getCount(cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pageBean.setTotalCount(totalCount);
        //4.封装总页数
        int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
        pageBean.setTotalPage(totalPage);
        //5.当前页显示的数据
        //当前页与起始索引index的关系
        int index=(currentPage-1)*currentCount;
        List<Product> list=null;
        try {
           list= dao.findProductByPage(cid,index,currentCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pageBean.setList(list);
        return pageBean;
    }

    public Product findProductByPid(String pid) {
        ProductDao dao=new ProductDao();
        Product product= null;
        try {
            product = dao.findProductByPid(pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
