package com.kampusmerdeka.officeorder.service;

import com.kampusmerdeka.officeorder.dto.repsonse.LoginResponse;
import com.kampusmerdeka.officeorder.dto.request.LoginRequest;
import com.kampusmerdeka.officeorder.entity.Customer;
import com.kampusmerdeka.officeorder.entity.Admin;
import com.kampusmerdeka.officeorder.repository.CustomerRepository;
import com.kampusmerdeka.officeorder.repository.UserRepository;
import com.kampusmerdeka.officeorder.security.TokenProvider;
import com.kampusmerdeka.officeorder.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthService {
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private CustomUserDetailService userDetailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<Object> login(LoginRequest loginRequest) {
        try {
            var username = "";
            var password = "";
            if (loginRequest.getUsername() != null) {
                username = loginRequest.getUsername();
            } else {
                username = loginRequest.getUsername();
                password = loginRequest.getPassword();
            }

            UserDetails userDetails = userDetailService.loadUserByUsername(username);
            if (!password.isBlank())
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = tokenProvider.generateToken(userDetails);

            LoginResponse response = LoginResponse.builder()
                    .tokenType("Bearer")
                    .token(token)
                    .username(userDetails.getUsername())
                    .role(userDetails.getAuthorities().stream().findFirst().get().toString())
                    .build();

            return ResponseUtil.ok("login success", response);
        } catch (UsernameNotFoundException exception) {
            log.error("Failed load user by username with error: {}", exception.getLocalizedMessage());
            return ResponseUtil.notFound("User not found");
        } catch (BadCredentialsException exception) {
            log.error("Failed to authenticate: {}", exception.getLocalizedMessage());
            return ResponseUtil.badRequest("Wrong password");
        }
    }

    public <T> T me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<? extends GrantedAuthority> grantedAuthority = authentication.getAuthorities().stream().findFirst();
        T result;

        try {
            String authority = grantedAuthority.get().toString();
            if (authority.equals(Admin.Role.CUSTOMER.name())) {
                Optional<Customer> customerOptional = customerRepository.findByEmail(username);

                if (customerOptional.isEmpty()) throw new AuthorizationServiceException("Unauthorized");
                return (T) customerOptional.get();
            } else {
                Optional<Admin> userOptional = userRepository.findByUsername(username);

                if (userOptional.isEmpty()) throw new AuthorizationServiceException("Unauthorized");
                return (T) userOptional.get();
            }
        } catch (AuthorizationServiceException exception) {
            log.error(exception.getLocalizedMessage());
            throw exception;
        }

    }
}