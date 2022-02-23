package com.events.database.dao;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.events.database.entity.User;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserDAOTest {

	@Autowired
	private UserDAO userDAO;
	//@Autowired
   // private PasswordEncoder passwordEncoder;

	
	@Before
    public void init() {
    }

	@Test
	@Order(1)
	@Rollback(value=false)
	public void saveUserTest() {
		User user = new User();
		user.setEmail("testcase@gmail.com");
		user.setUsername("testcase");
		user.setFirstName("test");
		user.setLastName("case");
		//String encryptedPassword = passwordEncoder.encode("123456");
       // user.setPassword(encryptedPassword);
		user.setPassword("123456");
		user.setPhone("503-555-9838");
		userDAO.save(user);
		
		Assertions.assertThat(user.getId()).isGreaterThan(0);

	}
	
	@Test
	@Order(2)
	@Rollback(value=false)
	public void getIngredientTest() {
		User user = userDAO.findById(1);
		
		Assertions.assertThat(user.getId()).isEqualTo(1);

	}
	
	@Test
	@Order(3)
	public void getListOfUsers() {
		List<User> users = userDAO.findAll();
		Assertions.assertThat(users.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateUserTest() {
		User user = userDAO.findById(22);
		user.setUsername("update user name");
		userDAO.save(user);
		Assertions.assertThat(userDAO.findById(22).getUsername()).isEqualTo(user.getUsername());
	}
	
	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteCategoryTest() {
		User user = userDAO.findById(22);
		userDAO.delete(user);
		Optional<User> optionalUser = Optional.ofNullable(userDAO.findById(user.getId()));

		User tempUser = null;
		if (optionalUser.isPresent()) {
			tempUser = optionalUser.get();
		}

		Assertions.assertThat(tempUser).isNull();
	}

}

