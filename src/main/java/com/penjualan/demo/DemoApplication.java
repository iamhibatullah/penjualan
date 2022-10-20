package com.penjualan.demo;

import com.penjualan.demo.dao.LoginRepository;
import com.penjualan.demo.entity.Login;
import com.penjualan.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	public static LoginService service;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
