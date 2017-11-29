package com.tsoft.app.security;

import com.tsoft.app.domain.User;
import com.tsoft.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        Optional<User> userFromDatabase = userRepository.findOneByEmail(login);
        return userFromDatabase.map(user -> {
            if (!user.getActivated()) {
                throw new UserNotActivatedException("User " + login + " was not activated");
            }
            List<GrantedAuthority> grantedAuthorities = new ArrayList();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getProfil().name()));
            return new org.springframework.security.core.userdetails.User(login,
                    user.getPassword(),
                    grantedAuthorities);
        }).orElseThrow(() -> new UsernameNotFoundException("User " + login + " was not found in the "
                + "database"));
    }

}
