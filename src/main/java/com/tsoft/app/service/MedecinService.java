/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsoft.app.service;

import com.tsoft.app.domain.Medecin;
import com.tsoft.app.domain.enumeration.Profil;
import com.tsoft.app.repository.MedecinRepository;
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
public class MedecinService {

    @Autowired
    MedecinRepository medecinRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Medecin createMedecin(Medecin medecin) throws Exception {
        medecin.setProfil(Profil.ROLE_MEDECIN);

        String encryptedPassword = passwordEncoder.encode("hta2017");
        medecin.setPassword(encryptedPassword);
        medecin.setResetKey(RandomUtil.generateResetKey());
        medecin.setResetDate(Instant.now());
        medecin.setActivated(true);

        return medecinRepository.save(medecin);
    }

    public Medecin updateMedecin(Medecin medecin) throws Exception {
        return medecinRepository.save(medecin);
    }

}
