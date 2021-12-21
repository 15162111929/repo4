package com.itheima.service;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao pruductDao;
    @Override
    public Product findByPid(Integer pid) {
      return pruductDao.findById(pid).get();
    }
}
