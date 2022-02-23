package com.events.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.events.database.dao.UserRoleDAO;
import com.events.database.entity.UserRole;

@Service
public class UserRoleService {

	@Autowired
	private UserRoleDAO repo;

	public List<UserRole> listAll(String keyword) {
		if (keyword != null) {
			return repo.search(keyword);
		}
		return repo.findAll();
	}

	public UserRole findById(Integer id) {
		return repo.findById(id);
	}


	public void save(UserRole user) {
		try {
			repo.save(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public List<UserRole> getUserRoles(Integer userId){
		List<UserRole> userRoles = repo.getUserRoles(userId);
		return userRoles;
	}
	
	public void delete(UserRole user) {
		try {
			repo.delete(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
