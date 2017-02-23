/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import org.ganeo.appli.hta.domain.Language;
import org.ganeo.appli.hta.domain.User;
import org.ganeo.appli.hta.repository.UserRepository;
import org.ganeo.appli.hta.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eisti
 */
/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    
   

   
    public Optional<User> completePasswordReset(String newPassword, String key) {
        log.debug("Reset user password for reset key {}", key);

        return userRepository.findOneByResetKey(key)
                .filter(user -> {
                    ZonedDateTime oneDayAgo = ZonedDateTime.now().minusHours(24);
                    return user.getResetDate().isAfter(oneDayAgo);
                })
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(newPassword));
                    user.setResetKey(null);
                    user.setResetDate(null);
                    userRepository.save(user);
                    return user;
                });
    }

    public Optional<User> requestPasswordReset(String mail) {
        return userRepository.findOneByEmail(mail)
                .filter(User::isActivated)
                .map(user -> {
                  //  user.setResetKey(RandomUtil.generateResetKey());
                    user.setResetDate(ZonedDateTime.now());
                    userRepository.save(user);
                    return user;
                });
    }

    public User createUserInformation(String login, String password, String firstName, String lastName, String email,
            Language langKey) {

        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setUsername(login);
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setPrenom(firstName);
        newUser.setNom(lastName);
        newUser.setEmail(email);
        newUser.setLangKey(langKey);
        // new user is not active
        newUser.setActivated(true);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public User createUser(User managedUserDTO) {
        if (managedUserDTO.getLangKey() == null) {
            managedUserDTO.setLangKey(Language.EN); // default language
        }
//        if (managedUserDTO.getAuthorities() != null) {
//            Set<Profil> authorities = new HashSet<>();
//            managedUserDTO.getAuthorities().stream().forEach(
//                    profil -> authorities.add(profilRepository.findOne(profil))
//            );
//            user.setProfils(authorities);
//        }
        String encryptedPassword = passwordEncoder.encode("123123123");
        managedUserDTO.setPassword(encryptedPassword);
      //  managedUserDTO.setResetKey(RandomUtil.generateResetKey());
        managedUserDTO.setResetDate(ZonedDateTime.now());
        managedUserDTO.setLoginAttempts(Byte.valueOf("0"));
        managedUserDTO.setActivated(true);
        userRepository.save(managedUserDTO);
        log.debug("Created Information for User: {}", managedUserDTO);
        return managedUserDTO;
    }

    public void updateUserInformation(String firstName, String lastName, String email, Language langKey) {
        userRepository.findOneByUsername(SecurityUtils.getCurrentUserLogin()).ifPresent(u -> {
            u.setPrenom(firstName);
            u.setNom(lastName);
            u.setEmail(email);
            u.setLangKey(langKey);
            userRepository.save(u);
            log.debug("Changed Information for User: {}", u);
        });
    }

    public void deleteUserInformation(String login) {
        userRepository.findOneByUsername(login).ifPresent(u -> {
            userRepository.delete(u);
            log.debug("Deleted User: {}", u);
        });
    }

    public void changePassword(String password) {
        userRepository.findOneByUsername(SecurityUtils.getCurrentUserLogin()).ifPresent(u -> {
            String encryptedPassword = passwordEncoder.encode(password);
            u.setPassword(encryptedPassword);
            userRepository.save(u);
            log.debug("Changed password for User: {}", u);
        });
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneByUsername(login).map(u -> {
      //      u.getAuthorities().size();
            return u;
        });
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities(Long id) {
        User user = userRepository.findOne(id);
       // user.getAuthorities().size(); // eagerly load the association
        return user;
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities() {
        User user = userRepository.findOneByUsername(SecurityUtils.getCurrentUserLogin()).get();
      //  user.getAuthorities().size(); // eagerly load the association
        return user;
    }

   

   

    public void resetFailAttempts(String name) throws Exception {
         userRepository.findOneByUsername(name).ifPresent(u -> {
            u.setLoginAttempts(new Byte("0"));
            u.setLastAccessDate(ZonedDateTime.now());
            userRepository.save(u);
        });
    }

    public void updateFailAttempts(String name) throws Exception {
        userRepository.findOneByUsername(name).ifPresent(u -> {
            u.setLoginAttempts((byte) (u.getLoginAttempts() + 1));
            userRepository.save(u);
        });
    }

}