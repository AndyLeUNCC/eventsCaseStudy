package com.events.database.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.events.database.entity.Connection;
import com.events.database.form.ConnectionProfileBean;
import com.events.database.form.IConnectionProfile;



public interface ConnectionDAO extends JpaRepository<Connection, Long> {

    public Connection findById(@Param("id") Integer id);

    public Connection findByName(@Param("name") String name);

    public List<Connection> findByLocation(@Param("location") String location);
   
    @Query(value= "select c.* from connections c where c.host_id = :host_id",  nativeQuery = true)
    List<Connection> findByHostId(@Param("host_id")  Integer hostId);
    
   @Query(value= "select con.id, con.name, cate.name as categoryName from connections con, categories cate "
  		+ "where con.category_id = cate.id and con.host_id = :host_id",  nativeQuery = true)
   // @Query(value= "select con.* from connections con, categories cate "
    //		+ "where con.category_id = cate.id and con.host_id = :host_id",  nativeQuery = true)
    List<IConnectionProfile> findConnectionsByHostId(@Param("host_id")  Integer hostId);
   //String is column name, Object is value of that column

    
    @Query("SELECT c FROM Connection c WHERE c.name LIKE %?1%"
            + " OR c.details LIKE %?1%"
            + " OR c.location LIKE %?1%"
            + " OR c.date LIKE %?1%"
            + " OR c.start_time LIKE %?1%"
            + " OR c.end_time LIKE %?1%")
    public List<Connection> search(String keyword);
    
}
