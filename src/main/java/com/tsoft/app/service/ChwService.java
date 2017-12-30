/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsoft.app.service;

import com.tsoft.app.config.properties.ApplicationProperties;
import com.tsoft.app.domain.Chw;
import com.tsoft.app.domain.enumeration.Profil;
import com.tsoft.app.repository.ChwRepository;
import com.tsoft.app.service.notification.MailService;
import com.tsoft.app.service.util.RandomUtil;
import java.time.Instant;
import javax.annotation.Resource;
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

    @Autowired
    MailService mailService;

    @Resource
    ApplicationProperties applicationProperties;

    public Chw createChw(Chw chw) throws Exception {
        chw.setProfil(Profil.ROLE_CHW);

        String encryptedPassword = passwordEncoder.encode(applicationProperties.getDefaultPassword());
        chw.setPassword(encryptedPassword);
        chw.setResetKey(RandomUtil.generateResetKey());
        chw.setResetDate(Instant.now());
        chw.setActivated(true);

        mailService.sendCreationEmail(chw);
        return chwRepository.save(chw);
    }

    public Chw updateChw(Chw chwNew) throws Exception {
        return chwRepository.findOneByEmail(chwNew.getEmail()).map(chw -> {
            chw.setNom(chwNew.getNom());
            chw.setPrenom(chwNew.getPrenom());
            chw.setTelephone(chwNew.getTelephone());
            chw.setDistrict(chwNew.getDistrict());
            chw.setTown(chwNew.getTown());
            chw.setActivated(chwNew.getActivated());
            return chwRepository.save(chw);
        }).orElse(null);

    }

}
