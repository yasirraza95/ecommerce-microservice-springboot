package com.springboot.ecommerce.user.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.springboot.ecommerce.user.model.Role;
import com.springboot.ecommerce.user.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

	@Autowired
	private RoleRepository repo;
	
	@Autowired
	private UserRepository userRepo;

	@Test
	public void testCreateRoles() {
		Role admin = new Role("ROLE_ADMIN");
		Role user = new Role("ROLE_USER");

		repo.saveAll(Arrays.asList(admin, user));

		long count = repo.count();
		assertEquals(2, count);
	}
	
	@Test
	public void testAssignRoleToUser() {
		Long userId = 9L;
		Integer roleId = 2;
		User user = userRepo.findOneById(userId);
		user.addRole(new Role(roleId));
		
		User updatedUser = userRepo.save(user);
		assertThat(updatedUser.getRoles()).hasSize(1);
	}
	
	@Test
	public void testAssignRoleToAdmin() {
		Long userId = 1L;
		
		User user = userRepo.findOneById(userId);
		user.addRole(new Role(1));
		user.addRole(new Role(2));
		
		User updatedUser = userRepo.save(user);
		assertThat(updatedUser.getRoles()).hasSize(2);
	}
}
