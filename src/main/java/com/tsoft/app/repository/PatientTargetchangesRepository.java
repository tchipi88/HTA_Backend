/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsoft.app.repository;

import com.tsoft.app.domain.PatientTargetchanges;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tchipi
 */
/**
 * Spring Data JPA repository for the PatientBloodplessure entity.
 */
public interface PatientTargetchangesRepository extends JpaRepository<PatientTargetchanges, Long> {

    public List<PatientTargetchanges> findByPatientId(Long patientId);

    public List<PatientTargetchanges> findByPatientIdOrderByDateTargetDesc(Long patientId);

    public PatientTargetchanges findFirstByPatientIdOrderByDateTargetDesc(Long patientId);

}
