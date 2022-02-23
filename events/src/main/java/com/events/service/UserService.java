package com.events.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.events.database.dao.ConnectionDAO;
import com.events.database.dao.UserDAO;
import com.events.database.dao.UserRoleDAO;
import com.events.database.entity.Connection;
import com.events.database.entity.User;
import com.events.database.entity.UserRole;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDAO userRepo;
	@Autowired
	private UserRoleDAO userRoleRepo;
	@Autowired
	private ConnectionDAO connectionRepo;

	public List<User> listAll(String keyword) {
		if (keyword != null) {
			return userRepo.search(keyword);
		}
		return userRepo.findAll();
	}

	public User findById(Integer id) {
		return userRepo.findById(id);
	}
	
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
		
	}

	public void save(User user) {
		try {
			userRepo.save(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public List<UserRole> getUserRoles(Integer userId){
		List<UserRole> userRoles = userRepo.getUserRoles(userId);
		return userRoles;
	}
	
	public void delete(User user) {
		try {
			List<UserRole> userRoles = userRoleRepo.getUserRoles(user.getId());
			System.out.println("<UserSerive> delete user Start");
			System.out.println("userRoles size " + userRoles.size());

			if (userRoles.size() > 0) {
				//delete the user roles of this user in the USER_ROLE table
				for(UserRole userRole : userRoles) {
					userRoleRepo.delete(userRole);
					System.out.println("<UserSerive> delete user has user roles in USER_ROLE table");
				}
				//delete the connection that user has created
				//find all of the connection record that user has created
				//Todo: need to implement to delete on the connection table
				List<Connection> connections = connectionRepo.findByHostId(user.getId());
				if (connections.size() > 0) {
					for(Connection connection : connections) {
						connectionRepo.delete(connection);
						System.out.println("<UserSerive> delete connections has created by this user");
					}
				}
				
				//delete the information of user in USER table
				userRepo.delete(user);
				System.out.println("<UserSerive> delete user End");
			} else {
				userRepo.delete(user);
				System.out.println("<UserSerive> delete user End");
			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
