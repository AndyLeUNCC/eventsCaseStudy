package com.events.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "connections")
public class Connection {

	@Id
	// this annotation is what tells hibernate that this variable is an auto
	// incremented primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "category_id")
	private Integer category_id;

	@Column(name = "name")
	private String name;

	@Column(name = "details")
	private String details;

	@Column(name = "location")
	private String location;

	@Column(name = "date")
	private String date;

	@Column(name = "start_time")
	private String start_time;
	
	@Column(name = "end_time")
	private String end_time;
	
	@Column(name = "image_url")
	private String image_url;

	@Column(name = "host_id")
	private int host_id;

	
	public Connection() {
		super();
	}
	

	public Connection(Integer category_id, String name, String details, String location, String date, String start_time,
			String end_time, String image_url, int host_id) {
		super();
		this.category_id = category_id;
		this.name = name;
		this.details = details;
		this.location = location;
		this.date = date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.image_url = image_url;
		this.host_id = host_id;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public int getHost_id() {
		return host_id;
	}

	public void setHost_id(int host_id) {
		this.host_id = host_id;
	}

	@Override
	public String toString() {
		return "Connection [id=" + id + ", category_id=" + category_id + ", name=" + name + ", details=" + details
				+ ", location=" + location + ", date=" + date + ", start_time=" + start_time + ", end_time=" + end_time
				+ ", image_url=" + image_url + ", host_id=" + host_id + "]";
	}
	

}
