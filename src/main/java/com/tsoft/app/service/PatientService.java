/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsoft.app.service;

import com.tsoft.app.domain.Chw;
import com.tsoft.app.domain.Patient;
import com.tsoft.app.domain.enumeration.Age;
import static com.tsoft.app.domain.enumeration.Age.CLASSE_1;
import static com.tsoft.app.domain.enumeration.Age.CLASSE_2;
import static com.tsoft.app.domain.enumeration.Age.CLASSE_3;
import static com.tsoft.app.domain.enumeration.Age.CLASSE_4;
import com.tsoft.app.domain.enumeration.Profil;
import com.tsoft.app.domain.enumeration.RecommandationFrequenceProgramSuvi;
import com.tsoft.app.domain.enumeration.RecommandationVisitDoctor;
import com.tsoft.app.domain.enumeration.Risque;
import com.tsoft.app.domain.enumeration.SystoliqueTranche;
import com.tsoft.app.repository.ChwRepository;
import com.tsoft.app.repository.PatientRepository;
import com.tsoft.app.security.SecurityUtils;
import com.tsoft.app.web.rest.vm.Dashboard;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
        if (patient.getChw() == null) {
            Chw chw = chwRepository.findOneByEmail(SecurityUtils.getCurrentUserLogin()).orElseThrow(() -> {
                return new Exception("Chw Inconnu");
            });
            patient.setChw(chw);
        }
        patient = patientRepository.save(patient);

        return patient;
    }

    public Patient updatePatient(Patient patient) throws Exception {
        return patientRepository.save(setRecommandation(patient));
    }

    private Patient setRecommandation(Patient patient) {
        assert patient != null;

        // determine Age of Patient
        long ageNumber = ChronoUnit.YEARS.between(patient.getBirthDay(), LocalDate.now());
        Age age = getClasse(ageNumber);
        SystoliqueTranche systoliqueTranche = getTranche(patient.getPaSystolique());
        Risque cvdRisk = null;

        switch (patient.getGender()) {
            case M: {
                if (patient.isDiabetique()) {
                    if (patient.isSmoker()) {
                        // smoker and diabetique
                        switch (age) {
                            case CLASSE_1: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }
                            case CLASSE_2: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }
                            case CLASSE_3: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }
                            case CLASSE_4: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }

                        }
                    } else {
                        // no smoker and diabetique
                        switch (age) {
                            case CLASSE_1: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }
                            case CLASSE_2: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }
                            case CLASSE_3: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }
                            case CLASSE_4: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }

                        }
                    }
                } else {
                    // no diabetique
                    if (patient.isSmoker()) {
                        // smoker and no diabetique
                        switch (age) {
                            case CLASSE_1: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }
                            case CLASSE_2: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }
                            case CLASSE_3: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }
                            case CLASSE_4: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }

                        }
                    } else {
                        // no smoker and no diabetique
                        switch (age) {
                            case CLASSE_1: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                break;
                            }
                            case CLASSE_2: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                break;
                            }
                            case CLASSE_3: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }
                            case CLASSE_4: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }

                        }
                    }
                }
                break;
            }

            case F: {
                if (patient.isDiabetique()) {
                    if (patient.isSmoker()) {
                        // smoker and diabetique
                        switch (age) {
                            case CLASSE_1: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }
                            case CLASSE_2: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }
                            case CLASSE_3: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }
                            case CLASSE_4: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }

                        }
                    } else {
                        // no smoker and diabetique
                        switch (age) {
                            case CLASSE_1: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                break;
                            }
                            case CLASSE_2: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }
                            case CLASSE_3: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }
                            case CLASSE_4: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }

                        }
                    }
                } else {
                    // no diabetique
                    if (patient.isSmoker()) {
                        // smoker and no diabetique
                        switch (age) {
                            case CLASSE_1: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                break;
                            }
                            case CLASSE_2: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }
                            case CLASSE_3: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }
                            case CLASSE_4: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }

                        }
                    } else {
                        // no smoker and no diabetique
                        switch (age) {
                            case CLASSE_1: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                break;
                            }
                            case CLASSE_2: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                break;
                            }
                            case CLASSE_3: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }
                            case CLASSE_4: {
                                if (SystoliqueTranche.TRANCHE_1.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_2.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.LOW;
                                }
                                if (SystoliqueTranche.TRANCHE_3.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.MEDIUM;
                                }
                                if (SystoliqueTranche.TRANCHE_4.equals(systoliqueTranche)) {
                                    cvdRisk = Risque.HIGH;
                                }
                                break;
                            }

                        }
                    }
                }
                break;
            }
        }

        patient.setCvdRisk(cvdRisk);

        if (patient.getPaSystolique() < 140) {
            patient.setRecommandationVisitDoctor(RecommandationVisitDoctor.BP_TBP);
        }
        if (patient.getPaSystolique() >= 140 && patient.getPaSystolique() <= 179
                && (Risque.MEDIUM.equals(cvdRisk) || Risque.LOW.equals(cvdRisk))) {
            patient.setRecommandationVisitDoctor(RecommandationVisitDoctor.CONFIRMATION);
        }
        if (patient.getPaSystolique() >= 180 || (Risque.HIGH.equals(cvdRisk))) {
            patient.setRecommandationVisitDoctor(RecommandationVisitDoctor.URGENT);
        }

        if (patient.getPaSystolique() >= 140 && patient.getPaSystolique() < 180
                && (Risque.MEDIUM.equals(cvdRisk) || Risque.LOW.equals(cvdRisk))) {
            patient.setRecommandationFrequenceProgramSuvi(RecommandationFrequenceProgramSuvi.MONTH);
        }
        if (patient.getPaSystolique() >= 180 && (Risque.MEDIUM.equals(cvdRisk) || Risque.LOW.equals(cvdRisk))) {
            patient.setRecommandationFrequenceProgramSuvi(RecommandationFrequenceProgramSuvi.TWO_WEEKS);
        }
        if (patient.getPaSystolique() >= 140 && (Risque.HIGH.equals(cvdRisk))) {
            patient.setRecommandationFrequenceProgramSuvi(RecommandationFrequenceProgramSuvi.TWO_WEEKS);
        }

        if (patient.getPaSystolique() >= 140) {
            patient.setRecommandationlifesytleAdvice(true);
        }

        return patient;
    }

    private SystoliqueTranche getTranche(Integer pa_systolique) {
        // if(pa_systolique==null) return SystoliqueTranche.TRANCHE_4;
        if (pa_systolique < 140) {
            return SystoliqueTranche.TRANCHE_1;
        }
        if (pa_systolique >= 140 && pa_systolique <= 159) {
            return SystoliqueTranche.TRANCHE_2;
        }
        if (pa_systolique >= 160 && pa_systolique <= 179) {
            return SystoliqueTranche.TRANCHE_3;
        }
        if (pa_systolique >= 180) {
            return SystoliqueTranche.TRANCHE_4;
        }
        return SystoliqueTranche.TRANCHE_4;
    }

    private Age getClasse(Long age) {
        if (age >= 40 && age <= 49) {
            return CLASSE_1;
        }
        if (age >= 50 && age <= 59) {
            return Age.CLASSE_2;
        }
        if (age >= 60 && age <= 69) {
            return Age.CLASSE_3;
        }
        if (age >= 70) {
            return Age.CLASSE_4;
        }
        return Age.CLASSE_4;

    }

    public Dashboard getDashboard(LocalDate fromDate, LocalDate toDate) {
        Dashboard dashboard = new Dashboard();
        ZonedDateTime fromZonedDateTime = fromDate.atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime toZonedDateTime = toDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1);

        long chwNbre = chwRepository.count();

        long patientsScreenedTotal = patientRepository.countByCreatedDateBetween(fromZonedDateTime, toZonedDateTime);

        dashboard.setPatientsScreenedTotal(patientsScreenedTotal);
        dashboard.setPatientsScreenedYou(patientRepository.countByCreatedDateBetweenAndCreatedBy(fromZonedDateTime, toZonedDateTime,
                SecurityUtils.getCurrentUserLogin()));
        dashboard.setPatientsScreenedAvg(patientsScreenedTotal / chwNbre);

        long patientsHighRiskTotal = patientRepository.countByCreatedDateBetweenAndCvdRisk(fromZonedDateTime, toZonedDateTime, Risque.HIGH);
        dashboard.setPatientsHighRiskTotal(patientsHighRiskTotal);
        dashboard.setPatientsHighRiskYou(patientRepository.countByCreatedDateBetweenAndCreatedByAndCvdRisk(fromZonedDateTime,
                toZonedDateTime, SecurityUtils.getCurrentUserLogin(), Risque.HIGH));
        dashboard.setPatientsHighRiskAvg(patientsHighRiskTotal / chwNbre);

        long patientsConsulteTotal = patientRepository.countByDateLastConsultationBetween(fromDate, toDate);
        dashboard.setPatientsConsulteTotal(patientsConsulteTotal);
        dashboard.setPatientsConsulteYou(
                patientRepository.countByDateLastConsultationBetweenAndCreatedBy(fromDate, toDate, SecurityUtils.getCurrentUserLogin()));
        dashboard.setPatientsConsulteAvg(patientsConsulteTotal / chwNbre);

        return dashboard;
    }

    public Dashboard getDashboardMedecin() {
        Dashboard dashboard = new Dashboard();
        dashboard.setPatientsScreenedTotal(patientRepository.countByMEdecin());
        dashboard.setPatientsHighRiskTotal(patientRepository.countByMEdecinHighRisk());
        return dashboard;
    }

    public Page<Patient> getPatients(String query, Pageable pageableRequest) {
        Sort sort = new Sort(new Sort.Order(Direction.DESC, "recommandationVisitDoctor"));
        Pageable pageable = new PageRequest(pageableRequest.getPageNumber(), pageableRequest.getPageSize(), sort);
        if (StringUtils.isBlank(query)) {
            if (SecurityUtils.isCurrentUserInRole(Profil.ROLE_ADMIN.name())) {
                return patientRepository.findAll(pageable);
            }
            if (SecurityUtils.isCurrentUserInRole(Profil.ROLE_CHW.name())) {
                return patientRepository.findAllByChwEmail(SecurityUtils.getCurrentUserLogin(), pageable);
            }
            if (SecurityUtils.isCurrentUserInRole(Profil.ROLE_MEDECIN.name())) {
                return patientRepository.findAllByChwMedecinEmail(SecurityUtils.getCurrentUserLogin(), pageable);
            }

        } else {
            if (query.equalsIgnoreCase("recommandation")) {
                if (SecurityUtils.isCurrentUserInRole(Profil.ROLE_ADMIN.name())) {
                    return patientRepository.findAllByRecommandationVisitDoctor(pageable);
                }
                if (SecurityUtils.isCurrentUserInRole(Profil.ROLE_CHW.name())) {
                    return patientRepository.findAllByChwRecommandationVisitDoctor(pageable);
                }
                if (SecurityUtils.isCurrentUserInRole(Profil.ROLE_MEDECIN.name())) {
                    return patientRepository.findAllByMedecinRecommandationVisitDoctor(pageable);
                }
            }
            if (query.equalsIgnoreCase("highRisk")) {
                if (SecurityUtils.isCurrentUserInRole(Profil.ROLE_ADMIN.name())) {
                    return patientRepository.findAllByHighRisk(pageable);
                }
                if (SecurityUtils.isCurrentUserInRole(Profil.ROLE_CHW.name())) {
                    return patientRepository.findAllByChwHighRisk(pageable);
                }
                if (SecurityUtils.isCurrentUserInRole(Profil.ROLE_MEDECIN.name())) {
                    return patientRepository.findAllByMedecinHighRisk(pageable);
                }
            }
            if (query.equalsIgnoreCase("isTreatement")) {
                if (SecurityUtils.isCurrentUserInRole(Profil.ROLE_ADMIN.name())) {
                    return patientRepository.findAllByBloodplessureTreatment(true, pageable);
                }
                if (SecurityUtils.isCurrentUserInRole(Profil.ROLE_CHW.name())) {
                    return patientRepository.findAllByChwIsTreatement(pageable);
                }
                if (SecurityUtils.isCurrentUserInRole(Profil.ROLE_MEDECIN.name())) {
                    return patientRepository.findAllByMEdecinIsTreatement(pageable);
                }
            }

            if (query.equalsIgnoreCase("bloodplessureTomesure")) {
                if (SecurityUtils.isCurrentUserInRole(Profil.ROLE_ADMIN.name())) {
                    return patientRepository.findAllByBloodplessureTomesure(true, pageable);
                }
                if (SecurityUtils.isCurrentUserInRole(Profil.ROLE_CHW.name())) {
                    return patientRepository.findAllByChwBloodplessureTomesure(pageable);
                }
                if (SecurityUtils.isCurrentUserInRole(Profil.ROLE_MEDECIN.name())) {
                    return patientRepository.findAllByMedecinBloodplessureTomesure(pageable);
                }
            }

        }
        return null;
    }
}
