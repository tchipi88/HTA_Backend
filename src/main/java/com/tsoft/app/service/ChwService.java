/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsoft.app.service;

import com.tsoft.app.domain.Chw;
import com.tsoft.app.domain.enumeration.Profil;
import com.tsoft.app.repository.ChwRepository;
import com.tsoft.app.service.util.RandomUtil;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tchipnangngansopa
 */
@Service
@Transactional
public class ChwService {

    @Autowired
    ChwRepository chwRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Chw createChw(Chw chw) throws Exception {
        chw.setProfil(Profil.ROLE_CHW);

        String encryptedPassword = passwordEncoder.encode("hta2017");
        chw.setPassword(encryptedPassword);
        chw.setResetKey(RandomUtil.generateResetKey());
        chw.setResetDate(Instant.now());
        chw.setActivated(true);

        return chwRepository.save(chw);
    }

    public Chw  updateChw(Chw chw) throws Exception {
        return chwRepository.save(chw);
    }

}
