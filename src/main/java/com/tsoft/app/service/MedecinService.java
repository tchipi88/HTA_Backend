/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsoft.app.service;

import com.tsoft.app.config.properties.ApplicationProperties;
import com.tsoft.app.domain.Medecin;
import com.tsoft.app.domain.enumeration.Profil;
import com.tsoft.app.repository.MedecinRepository;
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
public class MedecinService {
    
    @Autowired
    MedecinRepository medecinRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    MailService mailService;
    
    @Resource
    ApplicationProperties applicationProperties;
    
    public Medecin createMedecin(Medecin medecin) throws Exception {
        medecin.setProfil(Profil.ROLE_MEDECIN);
        
        String encryptedPassword = passwordEncoder.encode(applicationProperties.getDefaultPassword());
        medecin.setPassword(encryptedPassword);
        medecin.setResetKey(RandomUtil.generateResetKey());
        medecin.setResetDate(Instant.now());
        medecin.setActivated(true);
        
        mailService.sendCreationEmail(medecin);
        return medecinRepository.save(medecin);
    }
    
    public Medecin updateMedecin(Medecin medecinNew) throws Exception {
        return medecinRepository.findOneByEmail(medecinNew.getEmail()).map(medecin -> {
            medecin.setNom(medecinNew.getNom());
            medecin.setPrenom(medecinNew.getPrenom());
            medecin.setTelephone(medecinNew.getTelephone());
            medecin.setDistrict(medecinNew.getDistrict());
            medecin.setTown(medecinNew.getTown());
            medecin.setSpecialist(medecinNew.getSpecialist());
            medecin.setYearGraduation(medecinNew.getYearGraduation());
            medecin.setTitle(medecinNew.getTitle());
            medecin.setGender(medecinNew.getGender());
            medecin.setHealthCenter(medecinNew.getHealthCenter());
            medecin.setActivated(medecinNew.getActivated());
            return medecinRepository.save(medecin);
        }).orElse(null);
        
    }
    
}
