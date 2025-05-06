package com.ecommerce.QuickCarts.AuthService.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.QuickCarts.AuthService.Entity.User;
import com.ecommerce.QuickCarts.AuthService.security.JwtUtil;
import com.ecommerce.QuickCarts.AuthService.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	    @Autowired
	    private UserService userService;

	    @Autowired
	    private AuthenticationManager authManager;

	    @Autowired
	    private JwtUtil jwtUtil;

	    @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody User u) {
	        UsernamePasswordAuthenticationToken token =
	                new UsernamePasswordAuthenticationToken(u.getEmail(), u.getPassword());

	        try {
	            Authentication authenticate = authManager.authenticate(token);
	            if (authenticate.isAuthenticated()) {
	                String jwtToken = jwtUtil.generateToken(u.getEmail());
	                return new ResponseEntity<>(jwtToken, HttpStatus.OK);
	            }
	        } catch (Exception e) {
	            logger.error("Login failed for user: {}", u.getEmail(), e);
	        }
	        return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
	    }

	    @PostMapping("/register")
	    public ResponseEntity<String> registerUser(@RequestBody User u) {
	        boolean saveUser = userService.saveUser(u);

	        if (saveUser) {
	            return new ResponseEntity<>("Registered Successfully", HttpStatus.CREATED);
	        } else {
	            return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
		
	    @GetMapping("/")
	    public String home() {
	        return "Auth Service is running!";
	    }
	    
	}


