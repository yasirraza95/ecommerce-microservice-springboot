package com.yasir.microservices.gateway.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yasir.microservices.gateway.enums.Status;
import com.yasir.microservices.gateway.helper.UniqueEmail;
import com.yasir.microservices.gateway.helper.UniqueUsername;

@Entity
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, updatable = false)
	@UniqueUsername(message = "Username already exists")
	@NotNull(message = "Username is required")
	private String username;

	@NotNull(message = "Password is required")
	private String password;

	@NotNull(message = "Name is required")
	private String name;

	@NotNull(message = "Phone is required")
	@Size(min = 10, max = 10, message = "Length must be {min} digits only")
	@NumberFormat
	private String phone;

	@Column(unique = true)
	@UniqueEmail(message = "Email already exists")
	@NotNull(message = "Email is required")
	@Email(message = "Valid email is required")
	private String email;

	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Status is required")
	private Status activation = Status.INACTIVE;

	@Column
	private String resetPasswordToken;

	public User() {
	}

	public User(Long id, @NotNull(message = "Username is required") String username,
			@NotNull(message = "Name is required") String name, @NotNull(message = "Phone is required") String phone,
			@NotNull(message = "Email is required") @Email(message = "Valid email is required") String email) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.name = name;
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Status getActivation() {
		return activation;
	}

	public void setActivation(Status activation) {
		this.activation = activation;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
