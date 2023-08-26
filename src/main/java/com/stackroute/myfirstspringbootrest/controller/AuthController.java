package com.stackroute.myfirstspringbootrest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.myfirstspringbootrest.DTO.LoginUserDTO;
import com.stackroute.myfirstspringbootrest.exceptions.UserWithTheUserNameAlreadyPresentException;
import com.stackroute.myfirstspringbootrest.model.User;
import com.stackroute.myfirstspringbootrest.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationService authService;
	
	@GetMapping("")
	public String authHomeHandler() {
		return "home handler of auth controller invoked";
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginHandler(@RequestBody LoginUserDTO loginUser ){
		
		ResponseEntity<?> responseEntity;
		
		Map<String, String> tokenMap = new HashMap<>();
		
		boolean isUserValid = authService.verifyUser(loginUser.getUsername(),loginUser.getPassword());
		
		if(isUserValid) {
//			
			String token = authService.generateToken(loginUser.getUsername());
			tokenMap.put("token", token);
			responseEntity = new ResponseEntity<Map<String, String>>(tokenMap,HttpStatus.OK);
		} else {
			tokenMap.clear();
			tokenMap.put("token", null);
			tokenMap.put("message", "Invalid User Credentials");
			responseEntity = new ResponseEntity<Map<String,String>>(tokenMap,HttpStatus.FORBIDDEN);
		}
		
		return responseEntity;
//		return forbidden response;
		
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<?> registerNewUserHandler(@RequestBody User user){
		
		ResponseEntity<?> responseEntity;
		
		try {
			User newUser = authService.addNewUser(user);
			responseEntity = new ResponseEntity<User>(newUser,HttpStatus.CREATED);
		} catch (UserWithTheUserNameAlreadyPresentException e) {
			responseEntity = new ResponseEntity<String>("User Registration Failed",HttpStatus.CONFLICT);
		}
		
		return responseEntity;
	}
	
	

}
