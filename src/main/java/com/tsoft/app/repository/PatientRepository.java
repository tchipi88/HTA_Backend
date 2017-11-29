/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsoft.app.repository;

import com.tsoft.app.domain.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tchipi
 */
/**
 * Spring Data JPA repository for the Patient entity.
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("select p from Patient p where p.chw.email= ?#{ principal?.username }")
    public Page<Patient> findAllByChw(Pageable pageable);

     @Query("select p from Patient p where p.chw.medecin.email= ?#{ principal?.username }")
    public Page<Patient> findAllByMEdecin(Pageable pageable);

    

}




