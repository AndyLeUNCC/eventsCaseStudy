package com.events.database.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.events.database.entity.User;
import com.events.database.entity.UserRole;



public interface UserDAO extends JpaRepository<User, Long> {

    public User findById(@Param("id") Integer id);

    public User findByEmail(@Param("email") String email);

    public List<User> findByLastName(@Param("lastName") String lastName);

    public List<User> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    public List<User> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(@Param("firstName") String firstName, @Param("lastName") String lastName);

    //@Query("select u from User u where u.firstName = :firstName or u.lastName = :lastName")
    public List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("select u from User u where u.firstName = :firstName")
    public List<User> findByFirstName(String firstName);

    @Query("select u from User u where u.username = :username")
    public User findByUsername(@Param("username") String uname);

    @Query(value="SELECT u.* FROM user u WHERE u.first_name like '%:firstName%'", nativeQuery = true)
    public List<User> findByFirstNameLike(String firstName);

//    @Query("select ur from UserRole ur where ur.user.id = :userId")
//    List<UserRole> getUserRoles(Integer userId);
    
    @Query("select ur from UserRole ur where ur.user.id = :userId")
    List<UserRole> getUserRoles(@Param("userId")  Integer userId);
    
    
    @Query("SELECT u FROM User u WHERE u.firstName LIKE %?1%"
            + " OR u.username LIKE %?1%"
            + " OR u.firstName LIKE %?1%"
            + " OR u.lastName LIKE %?1%"
            + " OR u.email LIKE %?1%"
            + " OR u.phone LIKE %?1%")
    public List<User> search(String keyword);
    
//    @Query("SELECT u FROM User u WHERE u.firstName LIKE %?1%"
//            + " OR u.username LIKE %?1%"
//            + " OR u.firstName LIKE %?1%"
//            + " OR u.lastName LIKE %?1%"
//            + " OR u.email LIKE %?1%"
//            + " OR u.phone LIKE %?1%")
//    Page<User> search(String keyword, Pageable pageable);
    
}
