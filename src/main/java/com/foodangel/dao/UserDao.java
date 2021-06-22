package com.foodangel.dao;

import com.foodangel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByEmailAddress(String email);

    @Query(value = "SELECT * FROM users s WHERE s.user_type = 'angel' and s.id IN ( SELECT a.user_id FROM home_address a where a.zip_code = ?1)", nativeQuery = true)
    List<User> findAllByZipCode(int zipcode);
}
