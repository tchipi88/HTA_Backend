/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.repository;


import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import org.ganeo.appli.hta.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author eisti
 */
/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {



    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByUsername(String username);


    @Override
    void delete(User t);

}
