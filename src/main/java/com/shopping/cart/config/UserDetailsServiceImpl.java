package com.shopping.cart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopping.cart.entity.UserDtls;
import com.shopping.cart.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	 @Autowired
	 private UserRepository userRepoaitory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDtls user = userRepoaitory.findByEmail(username);
		 
		if(user==null) {
			throw new UsernameNotFoundException("user not found");
		}
		return new CustomUser(user);
	}
}
