package com.hyqin.dao;

import com.hyqin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description
 * @author: hyqin
 * @create : 2020/9/5
 */
public interface UserDao extends JpaRepository<User,Integer> {
}
