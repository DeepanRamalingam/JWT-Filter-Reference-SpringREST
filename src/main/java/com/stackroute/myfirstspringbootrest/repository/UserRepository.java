package com.stackroute.myfirstspringbootrest.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.myfirstspringbootrest.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByUsername(String username); 
	public Optional<User> findByUsernameAndPassword(String username, String password);
}
