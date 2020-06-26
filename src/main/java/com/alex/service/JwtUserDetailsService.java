package com.alex.service;

import com.alex.dao.RoleRepository;
import com.alex.dao.UserRepository;
import com.alex.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(userDao.findUserByUsername(user.getUsername()).get(0)));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantedAuthorities);
	}
	
	public User save(User user) {
		if(userDao.findByUsername(user.getUsername()) != null)
			throw new UsernameNotFoundException("User with name already exist!");
		user.setRoleId(roleRepository.findById(2).get());
		user.setCreatedAt(LocalDate.now());
		user.setUpdatedAt(LocalDate.now());
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userDao.save(user);
	}

}