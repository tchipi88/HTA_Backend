/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsoft.app.service;

import com.tsoft.app.domain.Chw;
import com.tsoft.app.domain.Patient;
import com.tsoft.app.repository.ChwRepository;
import com.tsoft.app.repository.PatientRepository;
import com.tsoft.app.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tchipnangngansopa
 */
@Service
@Transactional
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    ChwRepository chwRepository;

    public Patient createPatient(Patient patient) throws Exception {
        Chw chw = chwRepository.findOneByEmail(SecurityUtils.getCurrentUserLogin()).orElseThrow(() -> {
            return new Exception("Chw Inconnu");
        });
        patient.setChw(chw);
        patient = patientRepository.save(patient);

        return patient;
    }

    public Patient updatePatient(Patient patient) throws Exception {

        return patientRepository.save(patient);
    }

}
