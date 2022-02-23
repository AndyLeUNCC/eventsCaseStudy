package com.events.database.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.events.database.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, Long> {

    public Category findById(@Param("id") Integer id);
    public List<Category> findByName(@Param("name") String lastName);

    @Query("SELECT c FROM Category c WHERE c.name LIKE %?1%")
    public List<Category> search(String keyword);
   
    
}
