package com.hyqin.dao;

import com.hyqin.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.lang.Integer;
/**
 * @description
 * @author: hyqin
 * @create : 2020/9/5
 */
@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {
}
