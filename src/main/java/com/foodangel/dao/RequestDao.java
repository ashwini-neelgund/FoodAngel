package com.foodangel.dao;

import com.foodangel.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestDao extends JpaRepository<Request, Long> {

    @Query(
            value = "SELECT * FROM requests r WHERE r.status = 'active' and r.angel_id IS NUll and " +
                    "r.seeker_id IN ( SELECT u.id FROM users u join home_address a on u.id = a.user_id where u.user_type = 'seeker' and a.zip_code = ?1)",
            nativeQuery = true)
    List<Request> findByStatusAndAngelIdAndZipcode(int zipCode);

    @Query(value = "SELECT * FROM requests r WHERE r.status = 'assigned' and r.angel_id IN ( SELECT u.id FROM users u where u.email_address = ?1)",
            nativeQuery = true)
    List<Request> findByEmailAddressAndRequestStatus(String email);
}
