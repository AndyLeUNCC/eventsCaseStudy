package com.events.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.events.database.dao.RsvpDAO;
import com.events.database.entity.Rsvp;

@Service
public class RsvpService {

	@Autowired
	private RsvpDAO repo;

	public Rsvp findById(Integer id) {
		return repo.findById(id);
	}
	
	public void save(Rsvp rsvp) {
		try {
			repo.save(rsvp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public List<Rsvp> findByConnection(Integer connection) {
		return repo.findByConnection(connection);
		
	}
	
	public List<Rsvp> findByConnectionAndAttending(Integer connection, String attending) {
		return repo.findByConnectionAndAttending(connection, attending);
		
	}
	
	public Rsvp findByUserAndConnection(Integer hostId, Integer connection) {
		return repo.findByUserAndConnection(hostId, connection);
		
	}
	
	
	public List<Rsvp> findByUser(Integer hostId) {
		return repo.findByUser(hostId);
		
	}

	
	public void delete(Rsvp rsvp) {
		try {
			repo.delete(rsvp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
