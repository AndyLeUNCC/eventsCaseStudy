package com.events.database.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.events.database.entity.UserRole;



public interface UserRoleDAO extends JpaRepository<UserRole, Long> {

    public UserRole findById(@Param("id") Integer id);
  
//    @Query("select u from UserRole u where u.firstName = :firstName")
//    public List<UserRole> findByUserRole(String user_role);
    
    //@Query("select ur from UserRole ur where ur.user.id = :userId")
    @Query(value="SELECT ur.* FROM user_roles ur WHERE ur.user_id = :userId", nativeQuery = true)
    List<UserRole> getUserRoles(@Param("userId")  Integer userId);
    
    
    @Query("SELECT ur FROM UserRole ur WHERE ur.userRole LIKE %?1%")
    public List<UserRole> search(String keyword);
    
    
}
