package com.events.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.events.database.dao.ConnectionDAO;
import com.events.database.entity.Connection;
import com.events.database.form.ConnectionProfileBean;
import com.events.database.form.IConnectionProfile;
import com.events.database.form.IConnectionProfileRsvp;

@Service
public class ConnectionService {

	@Autowired
	private ConnectionDAO repo;

	public List<Connection> listAll(String keyword) {
		if (keyword != null) {
			return repo.search(keyword);
		}
		return repo.findAll();
	}

	public Connection findById(Integer id) {
		return repo.findById(id);
	}

	public void save(Connection connection) {
		try {
			repo.save(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Connection> findByHostId(Integer hostId) {
		return repo.findByHostId(hostId);

	}

	public List<IConnectionProfile> findConnectionsByHostId(Integer hostId) {
		return repo.findConnectionsByHostId(hostId);

	}
	
	public List<IConnectionProfileRsvp> findRsvpByUser(Integer hostId){
		return repo.findRsvpByUser(hostId);
	}

	public void delete(Connection connection) {
		try {
			repo.delete(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
