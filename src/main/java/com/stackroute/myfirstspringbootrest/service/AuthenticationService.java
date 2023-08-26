package com.stackroute.myfirstspringbootrest.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.myfirstspringbootrest.exceptions.UserWithTheUserNameAlreadyPresentException;
import com.stackroute.myfirstspringbootrest.model.User;
import com.stackroute.myfirstspringbootrest.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepo;
	
	public User addNewUser(User user) throws UserWithTheUserNameAlreadyPresentException {
		// TODO Auto-generated method stub
		Optional<User> optional = userRepo.findByUsername(user.getUsername());
		if(optional.isPresent()) {
			throw new UserWithTheUserNameAlreadyPresentException();
		}
		
		userRepo.save(user);
		return user;
	}

	public boolean verifyUser(String username, String password) {
		// TODO Auto-generated method stub
		return userRepo.findByUsernameAndPassword(username, password).isPresent();
	}

	public String generateToken(String username) {
		// TODO Auto-generated method stub
		String jwtToken;
		jwtToken = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 500000))
				.signWith(SignatureAlgorithm.HS256, "stackroute")
				.compact();
		
		return jwtToken;
	}

}
