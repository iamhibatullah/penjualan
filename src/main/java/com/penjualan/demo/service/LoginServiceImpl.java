package com.penjualan.demo.service;

import com.penjualan.demo.ApplicationUserDetails;
import com.penjualan.demo.PasswordConfiguration;
import com.penjualan.demo.dao.LoginRepository;
import com.penjualan.demo.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService, UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Login> optionalUser = loginRepository.findById(username);
        Login tempUser =  null;
        if(optionalUser.isPresent()){
            tempUser= optionalUser.get();
        }

        return new ApplicationUserDetails(tempUser);
    }

    @Override
    public void CreateUser(String username, String password) {
        String hashPassword = passwordEncoder.encode(password);
        Login user = new Login(username, hashPassword);
        loginRepository.save(user);
    }
}
