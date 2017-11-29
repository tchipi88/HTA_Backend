/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsoft.app.repository.search;

import com.tsoft.app.domain.Patient;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 *
 * @author tchipi
 */
/**
 * Spring Data JPA repository for the Patient entity.
 */
public interface PatientSearchRepository extends ElasticsearchRepository<Patient, Long> {

    

}




