package com.tsoft.app.service;

import com.tsoft.app.domain.User;
import com.tsoft.app.domain.enumeration.Profil;
import com.tsoft.app.repository.UserRepository;
import com.tsoft.app.security.SecurityUtils;
import com.tsoft.app.service.dto.UserDTO;
import com.tsoft.app.service.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.*;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public final JdbcTokenStore jdbcTokenStore;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,JdbcTokenStore jdbcTokenStore
            ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jdbcTokenStore = jdbcTokenStore;

    }

    public Optional<User> completePasswordReset(String newPassword, String key) {
        log.debug("Reset user password for reset key {}", key);

        return userRepository.findOneByResetKey(key)
                .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(newPassword));
                    user.setResetKey(null);
                    user.setResetDate(null);
                    return user;
                });
    }

    public Optional<User> requestPasswordReset(String mail) {
        return userRepository.findOneByEmail(mail)
                .filter(User::getActivated)
                .map(user -> {
                    user.setResetKey(RandomUtil.generateResetKey());
                    user.setResetDate(Instant.now());
                    return user;
                });
    }

    public User createUser(String password, String firstName, String lastName, String email, Profil profil, String telephone) {

        User newUser = new User();

        String encryptedPassword = passwordEncoder.encode(password);
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setEmail(email);
        newUser.setPrenom(firstName);
        newUser.setNom(lastName);
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key

        newUser.setProfil(profil);
        newUser.setTelephone(telephone);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPrenom(userDTO.getPrenom());
        user.setNom(userDTO.getNom());
        user.setProfil(userDTO.getProfil());

        String encryptedPassword = passwordEncoder.encode("hta2017");
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(Instant.now());
        user.setTelephone(userDTO.getTelephone());
        user.setActivated(true);
        userRepository.save(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    /**
     * Update basic information (first name, last name, email, language) for the
     * current user.
     *
     * @param firstName first name of user
     * @param lastName last name of user
     * @param email email id of user
     * @param langKey language key
     * @param imageUrl image URL of user
     */
    public void updateUser(String firstName, String lastName) {
        userRepository.findOneByEmail(SecurityUtils.getCurrentUserLogin()).ifPresent(user -> {
            user.setPrenom(firstName);
            user.setNom(lastName);
            log.debug("Changed Information for User: {}", user);
        });
    }

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update
     * @return updated user
     */
    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        return Optional.of(userRepository
                .findOne(userDTO.getId()))
                .map(user -> {
                    user.setEmail(userDTO.getEmail());
                    user.setPrenom(userDTO.getPrenom());
                    user.setNom(userDTO.getNom());
                    user.setActivated(userDTO.isActivated());
                    user.setTelephone(userDTO.getTelephone());

                    log.debug("Changed Information for User: {}", user);
                    return user;
                })
                .map(UserDTO::new);
    }

    public void deleteUser(String login) {
        jdbcTokenStore.findTokensByUserName(login).forEach(token
                -> jdbcTokenStore.removeAccessToken(token));
        userRepository.findOneByEmail(login).ifPresent(user -> {
            userRepository.delete(user);
            log.debug("Deleted User: {}", user);
        });
    }

    public void changePassword(String password) {
        userRepository.findOneByEmail(SecurityUtils.getCurrentUserLogin()).ifPresent(user -> {
            String encryptedPassword = passwordEncoder.encode(password);
            user.setPassword(encryptedPassword);
            log.debug("Changed password for User: {}", user);
        });
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllManagedUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDTO::new);
    }

    
    @Transactional(readOnly = true)
    public User getUserWithAuthorities() {
        return userRepository.findOneByEmail(SecurityUtils.getCurrentUserLogin()).orElse(null);
    }

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     * </p>
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        ZonedDateTime now = ZonedDateTime.now();
        List<User> users = userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(now.minusDays(3));
        for (User user : users) {
            log.debug("Deleting not activated user {}", user.getEmail());
            userRepository.delete(user);
        }
    }

}
