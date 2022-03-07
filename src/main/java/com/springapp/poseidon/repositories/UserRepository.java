package com.springapp.poseidon.repositories;

import com.springapp.poseidon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    @Query(value = "select * from users where username = :userName", nativeQuery = true)
    Optional<User> findByUserName(@Param("userName") String userName);

}
