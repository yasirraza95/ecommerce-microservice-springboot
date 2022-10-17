package com.yasir.microservices.gateway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yasir.microservices.gateway.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findOneByUsernameAndPassword(String username, String password);
	
	@Query(value = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password AND u.activation = 'ACTIVE'")
	public User findLogin(@Param("username") String username, @Param("password") String password);
	public User findOneByEmail(String email);
	
	public User findOneByUsername(String username);
	@Query(value = "SELECT u FROM User u WHERE u.username = :username AND u.activation = 'INACTIVE'")
	public User findAlreadyActive(String username);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE User u SET u.activation = 'ACTIVE' WHERE u.id = :id")
	public void updateActiveProfile(@Param("id") Long id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE User u SET u.resetPasswordToken = :token WHERE u.email = :email")
	public void updateForgotToken(@Param("token") String token, @Param("email") String email);
	
	public User findOneById(Long id);
	public User findOneByResetPasswordToken(String token);
	public User findByEmail(String email);
	public Optional<User> findByUsername(String username);

//	public User findByUsername(String username);
	public Boolean existsByUsername(String username);
	public Boolean existsByEmail(String email);
	public Boolean existsByResetPasswordToken(String token);
}
