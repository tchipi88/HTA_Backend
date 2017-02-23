/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.controller;

import org.ganeo.appli.hta.domain.CHW;
import org.ganeo.appli.hta.repository.CHWRepository;


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
 * REST controller for managing CHW.
 */
@RestController
@RequestMapping("/api")
public class CHWController {

    private final Logger log = LoggerFactory.getLogger(CHWController.class);

    private static final String ENTITY_NAME = "chw";
        
    private final CHWRepository chwRepository;

    public CHWController(CHWRepository chwRepository) {
        this.chwRepository = chwRepository;
    }

    /**
     * POST  /chws : Create a new chw.
     *
     * @param chw the chw to create
     * @return the ResponseEntity with status 201 (Created) and with body the new chw, or with status 400 (Bad Request) if the chw has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/chws")
    public ResponseEntity<CHW> createCHW(@Valid @RequestBody CHW chw) throws URISyntaxException {
        log.debug("REST request to save CHW : {}", chw);
        if (chw.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new chw cannot already have an ID")).body(null);
        }
        CHW result = chwRepository.save(chw);
        return ResponseEntity.created(new URI("/api/chws/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /chws : Updates an existing chw.
     *
     * @param chw the chw to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated chw,
     * or with status 400 (Bad Request) if the chw is not valid,
     * or with status 500 (Internal Server Error) if the chw couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/chws")
    public ResponseEntity<CHW> updateCHW(@Valid @RequestBody CHW chw) throws URISyntaxException {
        log.debug("REST request to update CHW : {}", chw);
        if (chw.getId() == null) {
            return createCHW(chw);
        }
        CHW result = chwRepository.save(chw);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, chw.getId().toString()))
            .body(result);
    }

    /**
     * GET  /chws : get all the chws.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of chws in body
     */
    @GetMapping("/chws")
    public List<CHW> getAllCHWs() {
        log.debug("REST request to get all CHWs");
        List<CHW> chws = chwRepository.findAll();
        return chws;
    }

    /**
     * GET  /chws/:id : get the "id" chw.
     *
     * @param id the id of the chw to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the chw, or with status 404 (Not Found)
     */
    @GetMapping("/chws/{id}")
    public ResponseEntity<CHW> getCHW(@PathVariable Long id) {
        log.debug("REST request to get CHW : {}", id);
        CHW chw = chwRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(chw));
    }

    /**
     * DELETE  /chws/:id : delete the "id" chw.
     *
     * @param id the id of the chw to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/chws/{id}")
    public ResponseEntity<Void> deleteCHW(@PathVariable Long id) {
        log.debug("REST request to delete CHW : {}", id);
        chwRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}