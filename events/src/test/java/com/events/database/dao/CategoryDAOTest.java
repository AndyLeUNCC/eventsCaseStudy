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

import com.events.database.entity.Category;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryDAOTest {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Before
    public void init() {
    }

	@Test
	@Order(1)
	@Rollback(value=false)
	public void saveCategoryTest() {
		Category category = new Category("New Category");
		categoryDAO.save(category);
		
		Assertions.assertThat(category.getId()).isGreaterThan(0);

	}
	
	@Test
	@Order(2)
	@Rollback(value=false)
	public void getIngredientTest() {
		Category category = categoryDAO.findById(1);
		
		Assertions.assertThat(category.getId()).isEqualTo(1);

	}
	
	@Test
	@Order(3)
	public void getListOfCategories() {
		List<Category> categories = categoryDAO.findAll();
		Assertions.assertThat(categories.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateCategoryTest() {
		Category category = categoryDAO.findById(16);
		category.setName("Update category");
		Assertions.assertThat(categoryDAO.findById(16).getName()).isEqualTo(category.getName());
	}
	
	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteCategoryTest() {
		Category category = categoryDAO.findById(16);
		categoryDAO.delete(category);
		Optional<Category> optionalCategory = Optional.ofNullable(categoryDAO.findById(category.getId()));

		Category tempCategory = null;
		if (optionalCategory.isPresent()) {
			tempCategory = optionalCategory.get();
		}

		Assertions.assertThat(tempCategory).isNull();
	}

}

