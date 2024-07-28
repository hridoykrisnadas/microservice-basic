package com.hridoykrisna.userservice.repository;

import com.hridoykrisna.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User where userId=?1 and isActive=true")
    Optional<User> findByIdAndIsActiveTrue(long userId);

    @Query("from User where isActive=true")
    List<User> findAllAndIsActiveTrue();
}
