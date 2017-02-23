/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.controller;

import org.ganeo.appli.hta.domain.ProgramHygienoDietetique;
import org.ganeo.appli.hta.repository.ProgramHygienoDietetiqueRepository;


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
 * REST controller for managing ProgramHygienoDietetique.
 */
@RestController
@RequestMapping("/api")
public class ProgramHygienoDietetiqueController {

    private final Logger log = LoggerFactory.getLogger(ProgramHygienoDietetiqueController.class);

    private static final String ENTITY_NAME = "programhygienodietetique";
        
    private final ProgramHygienoDietetiqueRepository programhygienodietetiqueRepository;

    public ProgramHygienoDietetiqueController(ProgramHygienoDietetiqueRepository programhygienodietetiqueRepository) {
        this.programhygienodietetiqueRepository = programhygienodietetiqueRepository;
    }

    /**
     * POST  /programhygienodietetiques : Create a new programhygienodietetique.
     *
     * @param programhygienodietetique the programhygienodietetique to create
     * @return the ResponseEntity with status 201 (Created) and with body the new programhygienodietetique, or with status 400 (Bad Request) if the programhygienodietetique has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/programhygienodietetiques")
    public ResponseEntity<ProgramHygienoDietetique> createProgramHygienoDietetique(@Valid @RequestBody ProgramHygienoDietetique programhygienodietetique) throws URISyntaxException {
        log.debug("REST request to save ProgramHygienoDietetique : {}", programhygienodietetique);
        if (programhygienodietetique.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new programhygienodietetique cannot already have an ID")).body(null);
        }
        ProgramHygienoDietetique result = programhygienodietetiqueRepository.save(programhygienodietetique);
        return ResponseEntity.created(new URI("/api/programhygienodietetiques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /programhygienodietetiques : Updates an existing programhygienodietetique.
     *
     * @param programhygienodietetique the programhygienodietetique to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated programhygienodietetique,
     * or with status 400 (Bad Request) if the programhygienodietetique is not valid,
     * or with status 500 (Internal Server Error) if the programhygienodietetique couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/programhygienodietetiques")
    public ResponseEntity<ProgramHygienoDietetique> updateProgramHygienoDietetique(@Valid @RequestBody ProgramHygienoDietetique programhygienodietetique) throws URISyntaxException {
        log.debug("REST request to update ProgramHygienoDietetique : {}", programhygienodietetique);
        if (programhygienodietetique.getId() == null) {
            return createProgramHygienoDietetique(programhygienodietetique);
        }
        ProgramHygienoDietetique result = programhygienodietetiqueRepository.save(programhygienodietetique);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, programhygienodietetique.getId().toString()))
            .body(result);
    }

    /**
     * GET  /programhygienodietetiques : get all the programhygienodietetiques.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of programhygienodietetiques in body
     */
    @GetMapping("/programhygienodietetiques")
    public List<ProgramHygienoDietetique> getAllProgramHygienoDietetiques() {
        log.debug("REST request to get all ProgramHygienoDietetiques");
        List<ProgramHygienoDietetique> programhygienodietetiques = programhygienodietetiqueRepository.findAll();
        return programhygienodietetiques;
    }

    /**
     * GET  /programhygienodietetiques/:id : get the "id" programhygienodietetique.
     *
     * @param id the id of the programhygienodietetique to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the programhygienodietetique, or with status 404 (Not Found)
     */
    @GetMapping("/programhygienodietetiques/{id}")
    public ResponseEntity<ProgramHygienoDietetique> getProgramHygienoDietetique(@PathVariable Long id) {
        log.debug("REST request to get ProgramHygienoDietetique : {}", id);
        ProgramHygienoDietetique programhygienodietetique = programhygienodietetiqueRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(programhygienodietetique));
    }

    /**
     * DELETE  /programhygienodietetiques/:id : delete the "id" programhygienodietetique.
     *
     * @param id the id of the programhygienodietetique to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/programhygienodietetiques/{id}")
    public ResponseEntity<Void> deleteProgramHygienoDietetique(@PathVariable Long id) {
        log.debug("REST request to delete ProgramHygienoDietetique : {}", id);
        programhygienodietetiqueRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}