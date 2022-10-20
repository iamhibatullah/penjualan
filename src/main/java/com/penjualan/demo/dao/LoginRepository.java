package com.penjualan.demo.dao;

import com.penjualan.demo.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, String> {
}
