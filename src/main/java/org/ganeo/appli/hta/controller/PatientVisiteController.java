/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.controller;

import org.ganeo.appli.hta.domain.PatientVisite;
import org.ganeo.appli.hta.repository.PatientVisiteRepository;


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
 * REST controller for managing PatientVisite.
 */
@RestController
@RequestMapping("/api")
public class PatientVisiteController {

    private final Logger log = LoggerFactory.getLogger(PatientVisiteController.class);

    private static final String ENTITY_NAME = "patientvisite";
        
    private final PatientVisiteRepository patientvisiteRepository;

    public PatientVisiteController(PatientVisiteRepository patientvisiteRepository) {
        this.patientvisiteRepository = patientvisiteRepository;
    }

    /**
     * POST  /patientvisites : Create a new patientvisite.
     *
     * @param patientvisite the patientvisite to create
     * @return the ResponseEntity with status 201 (Created) and with body the new patientvisite, or with status 400 (Bad Request) if the patientvisite has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/patientvisites")
    public ResponseEntity<PatientVisite> createPatientVisite(@Valid @RequestBody PatientVisite patientvisite) throws URISyntaxException {
        log.debug("REST request to save PatientVisite : {}", patientvisite);
        if (patientvisite.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new patientvisite cannot already have an ID")).body(null);
        }
        PatientVisite result = patientvisiteRepository.save(patientvisite);
        return ResponseEntity.created(new URI("/api/patientvisites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /patientvisites : Updates an existing patientvisite.
     *
     * @param patientvisite the patientvisite to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated patientvisite,
     * or with status 400 (Bad Request) if the patientvisite is not valid,
     * or with status 500 (Internal Server Error) if the patientvisite couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/patientvisites")
    public ResponseEntity<PatientVisite> updatePatientVisite(@Valid @RequestBody PatientVisite patientvisite) throws URISyntaxException {
        log.debug("REST request to update PatientVisite : {}", patientvisite);
        if (patientvisite.getId() == null) {
            return createPatientVisite(patientvisite);
        }
        PatientVisite result = patientvisiteRepository.save(patientvisite);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, patientvisite.getId().toString()))
            .body(result);
    }

    /**
     * GET  /patientvisites : get all the patientvisites.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of patientvisites in body
     */
    @GetMapping("/patientvisites")
    public List<PatientVisite> getAllPatientVisites() {
        log.debug("REST request to get all PatientVisites");
        List<PatientVisite> patientvisites = patientvisiteRepository.findAll();
        return patientvisites;
    }

    /**
     * GET  /patientvisites/:id : get the "id" patientvisite.
     *
     * @param id the id of the patientvisite to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the patientvisite, or with status 404 (Not Found)
     */
    @GetMapping("/patientvisites/{id}")
    public ResponseEntity<PatientVisite> getPatientVisite(@PathVariable Long id) {
        log.debug("REST request to get PatientVisite : {}", id);
        PatientVisite patientvisite = patientvisiteRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(patientvisite));
    }

    /**
     * DELETE  /patientvisites/:id : delete the "id" patientvisite.
     *
     * @param id the id of the patientvisite to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/patientvisites/{id}")
    public ResponseEntity<Void> deletePatientVisite(@PathVariable Long id) {
        log.debug("REST request to delete PatientVisite : {}", id);
        patientvisiteRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}