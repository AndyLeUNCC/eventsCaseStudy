package com.events.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rsvp")
public class Rsvp {

	@Id
	// this annotation is what tells hibernate that this variable is an auto
	// incremented primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "user")
	private Integer user;

	@Column(name = "connection")
	private Integer connection;

	@Column(name = "attending")
	private String attending;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Integer getConnection() {
		return connection;
	}

	public void setConnection(Integer connection) {
		this.connection = connection;
	}

	public String getAttending() {
		return attending;
	}

	public void setAttending(String attending) {
		this.attending = attending;
	}

	@Override
	public String toString() {
		return "Rsvp [id=" + id + ", user=" + user + ", connection=" + connection + ", attending=" + attending + "]";
	}

	
	

}
