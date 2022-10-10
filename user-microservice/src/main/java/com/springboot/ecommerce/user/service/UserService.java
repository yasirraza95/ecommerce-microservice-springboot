package com.springboot.ecommerce.user.service;

import java.util.List;
import org.springframework.validation.annotation.Validated;

import com.springboot.ecommerce.user.exception.NotFoundException;
import com.springboot.ecommerce.user.model.User;

@Validated
public interface UserService {

	public List<User> getUsers();

	public String login(User user);

	public User signup(User user);

	public User getProfile(String id) throws NotFoundException;

	public User updateProfile(String id, User user);

	public void updateResetPasswordToken(String token, String email);

	public User getByResetPasswordToken(String token);

	public void updatePassword(User user, String newPass);

	public void activateProfile(String user);

	public void forgotPassword(User email);

	public void checkToken(String token);

}
