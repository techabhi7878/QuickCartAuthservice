package com.ecommerce.QuickCarts.AuthService.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.QuickCarts.AuthService.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	 Optional<User> findByUsername(String username);
	    Optional<User> findByEmail(String email);
	    Optional<User> findByPhoneNumber(String phoneNumber);
}
