/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsoft.app.init;

import com.tsoft.app.domain.User;
import com.tsoft.app.domain.enumeration.Profil;
import com.tsoft.app.repository.UserRepository;
import com.tsoft.app.service.util.RandomUtil;
import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tchipi
 */
@Component
@Transactional
public class SecurityRealData implements RealData {

    @Autowired
    UserRepository UserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void populateData(HttpServletRequest req) throws Exception {

        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode("admin");
        newUser.setEmail("ganeo.app@gmail.com");
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setNom("admin");
        newUser.setTown("Paris");
        // new user is not active
        newUser.setActivated(true);
        // new user gets registration key
        newUser.setProfil(Profil.ROLE_ADMIN);
        newUser.setResetKey(RandomUtil.generateResetKey());
        newUser.setResetDate(Instant.now());

        UserRepository.save(newUser);

    }

}
