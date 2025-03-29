package com.backend.splitwise.repositories;


import com.backend.splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByPhone(String phone);

//    @Query("SELECT u FROM users u WHERE u.phone = :phone")
//    Optional<User> findSingleByPhone(@Param("phone") String phone);
////
//    @Query("SELECT u FROM users u WHERE u.phone = :phone ORDER BY u.id ASC LIMIT 1")
//    Optional<User> findFirstByPhone(@Param("phone") String phone);


}
