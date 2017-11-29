package com.tsoft.app.repository;

import com.tsoft.app.domain.User;
import java.time.ZonedDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(ZonedDateTime dateTime);

    Optional<User> findOneByEmail(String email);


    Page<User> findAllByEmailNot(Pageable pageable, String email);

   Optional<User> findOneByResetKey(String resetKey);

}
