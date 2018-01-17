/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsoft.app.repository;

import com.tsoft.app.domain.PatientConsultation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tchipi
 */
/**
 * Spring Data JPA repository for the PatientConsultation entity.
 */
public interface PatientConsultationRepository extends JpaRepository<PatientConsultation, Long> {

    public PatientConsultation findFirstByPatientIdOrderByDateConsultationDesc(Long patientId);


}




