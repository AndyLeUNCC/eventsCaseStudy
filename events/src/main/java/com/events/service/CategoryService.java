package com.events.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.events.database.dao.CategoryDAO;
import com.events.database.entity.Category;

@Service
public class CategoryService {

	@Autowired
	private CategoryDAO repo;

	public List<Category> listAll(String keyword) {
		if (keyword != null) {
			return repo.search(keyword);
		}
		return repo.findAll();
	}

	public Category findById(Integer id) {
		return repo.findById(id);
	}
	
	public void save(Category category) {
		try {
			repo.save(category);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public void delete(Category category) {
		try {
			repo.delete(category);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
