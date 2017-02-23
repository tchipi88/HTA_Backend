/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.controller;

import org.ganeo.appli.hta.domain.PatientReleve;
import org.ganeo.appli.hta.repository.PatientReleveRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.ganeo.appli.hta.util.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


/**
 * REST controller for managing PatientReleve.
 */
@RestController
@RequestMapping("/api")
public class PatientReleveController {

    private final Logger log = LoggerFactory.getLogger(PatientReleveController.class);

    private static final String ENTITY_NAME = "patientreleve";
        
    private final PatientReleveRepository patientreleveRepository;

    public PatientReleveController(PatientReleveRepository patientreleveRepository) {
        this.patientreleveRepository = patientreleveRepository;
    }

    /**
     * POST  /patientreleves : Create a new patientreleve.
     *
     * @param patientreleve the patientreleve to create
     * @return the ResponseEntity with status 201 (Created) and with body the new patientreleve, or with status 400 (Bad Request) if the patientreleve has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/patientreleves")
    public ResponseEntity<PatientReleve> createPatientReleve(@Valid @RequestBody PatientReleve patientreleve) throws URISyntaxException {
        log.debug("REST request to save PatientReleve : {}", patientreleve);
        if (patientreleve.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new patientreleve cannot already have an ID")).body(null);
        }
        PatientReleve result = patientreleveRepository.save(patientreleve);
        return ResponseEntity.created(new URI("/api/patientreleves/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /patientreleves : Updates an existing patientreleve.
     *
     * @param patientreleve the patientreleve to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated patientreleve,
     * or with status 400 (Bad Request) if the patientreleve is not valid,
     * or with status 500 (Internal Server Error) if the patientreleve couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/patientreleves")
    public ResponseEntity<PatientReleve> updatePatientReleve(@Valid @RequestBody PatientReleve patientreleve) throws URISyntaxException {
        log.debug("REST request to update PatientReleve : {}", patientreleve);
        if (patientreleve.getId() == null) {
            return createPatientReleve(patientreleve);
        }
        PatientReleve result = patientreleveRepository.save(patientreleve);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, patientreleve.getId().toString()))
            .body(result);
    }

    /**
     * GET  /patientreleves : get all the patientreleves.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of patientreleves in body
     */
    @GetMapping("/patientreleves")
    public List<PatientReleve> getAllPatientReleves() {
        log.debug("REST request to get all PatientReleves");
        List<PatientReleve> patientreleves = patientreleveRepository.findAll();
        return patientreleves;
    }

    /**
     * GET  /patientreleves/:id : get the "id" patientreleve.
     *
     * @param id the id of the patientreleve to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the patientreleve, or with status 404 (Not Found)
     */
    @GetMapping("/patientreleves/{id}")
    public ResponseEntity<PatientReleve> getPatientReleve(@PathVariable Long id) {
        log.debug("REST request to get PatientReleve : {}", id);
        PatientReleve patientreleve = patientreleveRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(patientreleve));
    }

    /**
     * DELETE  /patientreleves/:id : delete the "id" patientreleve.
     *
     * @param id the id of the patientreleve to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/patientreleves/{id}")
    public ResponseEntity<Void> deletePatientReleve(@PathVariable Long id) {
        log.debug("REST request to delete PatientReleve : {}", id);
        patientreleveRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}