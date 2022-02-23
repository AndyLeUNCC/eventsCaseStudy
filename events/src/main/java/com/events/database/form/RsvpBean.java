package com.events.database.form;



public class RsvpBean extends FormBean {

    // this is a hidden data value and is only needed to distinguish an edit from a create
    private Integer id;

    private Integer user;
    private Integer connection;
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
		return "RsvpBean [id=" + id + ", user=" + user + ", connection=" + connection + ", attending=" + attending
				+ "]";
	}

	
   
}
