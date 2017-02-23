/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.controller;

import org.ganeo.appli.hta.domain.PatientSituation;
import org.ganeo.appli.hta.repository.PatientRecommandationRepository;


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
 * REST controller for managing PatientSituation.
 */
@RestController
@RequestMapping("/api")
public class PatientRecommandationController {

    private final Logger log = LoggerFactory.getLogger(PatientRecommandationController.class);

    private static final String ENTITY_NAME = "patientrecommandation";
        
    private final PatientRecommandationRepository patientrecommandationRepository;

    public PatientRecommandationController(PatientRecommandationRepository patientrecommandationRepository) {
        this.patientrecommandationRepository = patientrecommandationRepository;
    }

    /**
     * POST  /patientrecommandations : Create a new patientrecommandation.
     *
     * @param patientrecommandation the patientrecommandation to create
     * @return the ResponseEntity with status 201 (Created) and with body the new patientrecommandation, or with status 400 (Bad Request) if the patientrecommandation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/patientrecommandations")
    public ResponseEntity<PatientSituation> createPatientRecommandation(@Valid @RequestBody PatientSituation patientrecommandation) throws URISyntaxException {
        log.debug("REST request to save PatientRecommandation : {}", patientrecommandation);
        if (patientrecommandation.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new patientrecommandation cannot already have an ID")).body(null);
        }
        PatientSituation result = patientrecommandationRepository.save(patientrecommandation);
        return ResponseEntity.created(new URI("/api/patientrecommandations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /patientrecommandations : Updates an existing patientrecommandation.
     *
     * @param patientrecommandation the patientrecommandation to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated patientrecommandation,
     * or with status 400 (Bad Request) if the patientrecommandation is not valid,
     * or with status 500 (Internal Server Error) if the patientrecommandation couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/patientrecommandations")
    public ResponseEntity<PatientSituation> updatePatientRecommandation(@Valid @RequestBody PatientSituation patientrecommandation) throws URISyntaxException {
        log.debug("REST request to update PatientRecommandation : {}", patientrecommandation);
        if (patientrecommandation.getId() == null) {
            return createPatientRecommandation(patientrecommandation);
        }
        PatientSituation result = patientrecommandationRepository.save(patientrecommandation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, patientrecommandation.getId().toString()))
            .body(result);
    }

    /**
     * GET  /patientrecommandations : get all the patientrecommandations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of patientrecommandations in body
     */
    @GetMapping("/patientrecommandations")
    public List<PatientSituation> getAllPatientRecommandations() {
        log.debug("REST request to get all PatientRecommandations");
        List<PatientSituation> patientrecommandations = patientrecommandationRepository.findAll();
        return patientrecommandations;
    }

    /**
     * GET  /patientrecommandations/:id : get the "id" patientrecommandation.
     *
     * @param id the id of the patientrecommandation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the patientrecommandation, or with status 404 (Not Found)
     */
    @GetMapping("/patientrecommandations/{id}")
    public ResponseEntity<PatientSituation> getPatientRecommandation(@PathVariable Long id) {
        log.debug("REST request to get PatientRecommandation : {}", id);
        PatientSituation patientrecommandation = patientrecommandationRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(patientrecommandation));
    }

    /**
     * DELETE  /patientrecommandations/:id : delete the "id" patientrecommandation.
     *
     * @param id the id of the patientrecommandation to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/patientrecommandations/{id}")
    public ResponseEntity<Void> deletePatientRecommandation(@PathVariable Long id) {
        log.debug("REST request to delete PatientRecommandation : {}", id);
        patientrecommandationRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}