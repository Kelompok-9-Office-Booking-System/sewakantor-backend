package com.kampusmerdeka.officeorder.service;

import com.kampusmerdeka.officeorder.entity.Admin;
import com.kampusmerdeka.officeorder.entity.Customer;
import com.kampusmerdeka.officeorder.repository.CustomerRepository;
import com.kampusmerdeka.officeorder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = "";
        String role = "";

        if (username.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            Customer customer = customerRepository.findByEmail(username).orElse(null);
            if (customer == null) throw new UsernameNotFoundException(String.format("User %s not found", username));

            password = customer.getPassword();
            role = Admin.Role.CUSTOMER.name();
        } else {
            Admin user = userRepository.findByUsername(username).orElse(null);
            if (user == null) throw new UsernameNotFoundException(String.format("User %s not found", username));

            password = user.getPassword();
            role = user.getRole().name();
        }

        return new org.springframework.security.core.userdetails.User(
                username,
                password,
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );
    }
}