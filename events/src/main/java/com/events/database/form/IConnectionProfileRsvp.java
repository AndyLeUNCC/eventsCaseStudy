package com.events.database.form;

public interface IConnectionProfileRsvp {
	Integer getId();
	Integer getUser(); //user id
	Integer getConnection(); //connection id
	String getName(); //name of connection
	String getAttending(); // attending Yes, No, Maybe
	String getCategoryName(); //category name


}
