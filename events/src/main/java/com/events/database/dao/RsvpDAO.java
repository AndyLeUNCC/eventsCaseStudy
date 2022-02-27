package com.events.database.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.events.database.entity.Rsvp;
import com.events.database.form.IConnectionProfileRsvp;



public interface RsvpDAO extends JpaRepository<Rsvp, Long> {

    public Rsvp findById(@Param("id") Integer id);

    public List<Rsvp> findByConnection(@Param("connection") Integer connection);

    public List<Rsvp> findByUser(@Param("user") Integer user);
    
    public List<Rsvp> findByAttending(@Param("attending") String attending);

    public List<Rsvp> findByConnectionAndAttending(@Param("connection") Integer connection, 
    		@Param("attending") String attending);
    
    public Rsvp findByUserAndConnection(@Param("user") Integer user, @Param("connection") Integer connection);
    
    public Rsvp findByUserAndConnectionAndAttending(@Param("user") Integer user, 
    		@Param("connection") Integer connection, 
    		@Param("attending") String attending);

	/*
	 * @Query(value= "select c.* from connections c where c.host_id = :host_id",
	 * nativeQuery = true) List<Rsvp> findByConnection(@Param("host_id") Integer
	 * hostId);
	 */
  
    

   

    
}
