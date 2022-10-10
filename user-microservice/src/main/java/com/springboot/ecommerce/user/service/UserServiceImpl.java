package com.springboot.ecommerce.user.service;

import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.ecommerce.user.exception.CustomException;
import com.springboot.ecommerce.user.exception.NotFoundException;
import com.springboot.ecommerce.user.model.User;
import com.springboot.ecommerce.user.repository.UserRepository;
import com.springboot.ecommerce.user.security.JwtUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public List<User> getUsers() {
		return (List<User>) userRepo.findAll();
	}

//	@Override
//	public String login(User user) {
//		User exist = userRepo.findOneByUsernameAndPassword(user.getUsername(), user.getPassword());
//
//		if (exist != null) {
//			User activation = userRepo.findLogin(user.getUsername(), user.getPassword());
//			if (activation != null) {
//				String token = jwtUtil.generateToken(user.getUsername());
//				return token;
//
//			} else {
//				throw new CustomException("Activate your account");
//			}
//
//		} else {
//			throw new CustomException("Invalid username or password");
//		}
//
//	}

	//FIXME
	@Override
	public String login(User user) {
		try {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//			System.out.println(encoder.encode("aaAA11@@"));
			
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							user.getUsername(), user.getPassword())
					);
			
			User authUser = (User) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(authUser);
			return accessToken;
		} catch(BadCredentialsException e) {
			throw new BadCredentialsException(e.getMessage().toString());
		}
	}

	@Override
	public User signup(User user) {
		return userRepo.save(user);
	}

	@Override
	public User getProfile(String id) throws NotFoundException {
		User user;
		user = userRepo.findOneById(Long.parseLong(id));
//		logger.info("message="+user);
		if (user != null) {
			return user;
		} else {
//			return null;
			throw new NotFoundException("User not found");
		}
	}

	@Override
	public User updateProfile(String id, User user) {
		User updateUser = new User();
		updateUser.setId(Long.parseLong(id));
		updateUser.setName(user.getName());
		updateUser.setUsername(user.getUsername());
		updateUser.setEmail(user.getEmail());
		updateUser.setPassword(user.getPassword());
		updateUser.setPhone(user.getPhone());

		User dbUser = userRepo.findOneById(Long.parseLong(id));
		if (dbUser != null) {
			userRepo.saveAndFlush(updateUser);

			User newUser = userRepo.findOneById(Long.parseLong(id));
			return newUser;
		} else {
			throw new CustomException("User with id '" + id + "' not found.");
		}

	}

	@Override
	public void updateResetPasswordToken(String token, String email) {
		User user = userRepo.findOneByEmail(email);
		if (user != null) {
			user.setResetPasswordToken(token);
			userRepo.save(user);
		} else {
			throw new CustomException("No user found against email " + email);
		}
	}

	@Override
	public User getByResetPasswordToken(String token) {
		return userRepo.findOneByResetPasswordToken(token);
	}

	@Override
	public void updatePassword(User user, String newPass) {
		user.setPassword(newPass);
		userRepo.save(user);
	}

	@Override
	public void activateProfile(String user) {
		User exist = userRepo.findOneByUsername(user);
		if (exist != null) {
			User already = userRepo.findAlreadyActive(user);
			if (already != null) {
				userRepo.updateActiveProfile(exist.getId());
			} else {
				throw new CustomException("Profile already activated");
			}
		} else {
			throw new CustomException("User don't exist");
		}

	}

	@Override
	public void forgotPassword(User user) {
		boolean exist = userRepo.existsByEmail(user.getEmail());
		if (exist) {
			Date date = new Date();
			int code = (user.getEmail() + date.toString()).hashCode();
			String token = "";
			token = (code > 0 ? "A" : "B") + Math.abs(code);

			userRepo.updateForgotToken(token, user.getEmail());
			this.sendEmail(user.getEmail(), token);

		} else {
			throw new NotFoundException("No user found against the email");
		}
	}

	public String sendEmail(String recipent, String token) {
		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);

			helper.setSubject("Forgot Password");
			helper.setFrom("raza.yasir95@gmail.com");
			helper.setTo(recipent);
			helper.setText("<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
					+ "<p>Click the link below to change your password:</p>"
					+ "<p><a href=\"https://abc.com/reset_password?token=" + token + "\">Change my password</a></p>"
					+ "<br>" + "<p>Ignore this email if you do remember your password, "
					+ "or you have not made the request.</p>", true);

			this.mailSender.send(message);
			return "Email sent";
		} catch (MessagingException e) {
			return e.getMessage();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public void checkToken(String token) {
		boolean exist = userRepo.existsByResetPasswordToken(token);
		if (!exist) {
			throw new CustomException("Error validating account");
		}

	}
	
}
