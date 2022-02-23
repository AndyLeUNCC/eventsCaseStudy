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
import org.springframework.test.annotation.Rollback;

import com.events.database.entity.Connection;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ConnectionDAOTest {

	@Autowired
	private ConnectionDAO connectionDAO;
	
	@Before
    public void init() {
    }

	@Test
	@Order(1)
	@Rollback(value=false)
	public void saveConnectionTest() {
		Connection connection = new Connection();
		connection.setCategory_id(1);
		connection.setName("Connection Cocktail Event!");
		connection.setDetails("detail Description");
		connection.setLocation("location test");
		connection.setDate("2022-02-28");
		connection.setStart_time("09:30");
		connection.setEnd_time("12:00");
		connection.setImage_url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSru66MwHe3_TdzGjmUzNCye__TdeWjKMF09A&usqp=CAU");
		connection.setHost_id(1);
		
		connectionDAO.save(connection);
		
		Assertions.assertThat(connection.getId()).isGreaterThan(0);

	}
	
	@Test
	@Order(2)
	@Rollback(value=false)
	public void getIngredientTest() {
		Connection connection = connectionDAO.findById(1);
		
		Assertions.assertThat(connection.getId()).isEqualTo(1);

	}
	
	@Test
	@Order(3)
	public void getListOfConnection() {
		List<Connection> connections = connectionDAO.findAll();
		Assertions.assertThat(connections.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateConnectionTest() {
		Connection connection = connectionDAO.findById(32);
		connection.setName("Connection Cocktail Event!");
		Assertions.assertThat(connectionDAO.findById(32).getName()).isEqualTo(connection.getName());
	}
	
	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteConnectionTest() {
		Connection connection = connectionDAO.findById(32);
		connectionDAO.delete(connection);
		Optional<Connection> optionalConnection = Optional.ofNullable(connectionDAO.findById(connection.getId()));

		Connection tempConnection = null;
		if (optionalConnection.isPresent()) {
			tempConnection = optionalConnection.get();
		}

		Assertions.assertThat(tempConnection).isNull();
	}

}

