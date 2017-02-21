/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.repository;

import org.ganeo.appli.hta.model.PatientReleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author tchipnangngansopa
 */
@RepositoryRestResource
public interface PatientReleveRepository extends JpaRepository<PatientReleve, Long> {
    
}
