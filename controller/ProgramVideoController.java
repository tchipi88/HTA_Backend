/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.controller;

import org.ganeo.appli.hta.domain.ProgramVideo;
import org.ganeo.appli.hta.repository.ProgramVideoRepository;


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
 * REST controller for managing ProgramVideo.
 */
@RestController
@RequestMapping("/api")
public class ProgramVideoController {

    private final Logger log = LoggerFactory.getLogger(ProgramVideoController.class);

    private static final String ENTITY_NAME = "programvideo";
        
    private final ProgramVideoRepository programvideoRepository;

    public ProgramVideoController(ProgramVideoRepository programvideoRepository) {
        this.programvideoRepository = programvideoRepository;
    }

    /**
     * POST  /programvideos : Create a new programvideo.
     *
     * @param programvideo the programvideo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new programvideo, or with status 400 (Bad Request) if the programvideo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/programvideos")
    public ResponseEntity<ProgramVideo> createProgramVideo(@Valid @RequestBody ProgramVideo programvideo) throws URISyntaxException {
        log.debug("REST request to save ProgramVideo : {}", programvideo);
        if (programvideo.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new programvideo cannot already have an ID")).body(null);
        }
        ProgramVideo result = programvideoRepository.save(programvideo);
        return ResponseEntity.created(new URI("/api/programvideos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /programvideos : Updates an existing programvideo.
     *
     * @param programvideo the programvideo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated programvideo,
     * or with status 400 (Bad Request) if the programvideo is not valid,
     * or with status 500 (Internal Server Error) if the programvideo couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/programvideos")
    public ResponseEntity<ProgramVideo> updateProgramVideo(@Valid @RequestBody ProgramVideo programvideo) throws URISyntaxException {
        log.debug("REST request to update ProgramVideo : {}", programvideo);
        if (programvideo.getId() == null) {
            return createProgramVideo(programvideo);
        }
        ProgramVideo result = programvideoRepository.save(programvideo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, programvideo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /programvideos : get all the programvideos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of programvideos in body
     */
    @GetMapping("/programvideos")
    public List<ProgramVideo> getAllProgramVideos() {
        log.debug("REST request to get all ProgramVideos");
        List<ProgramVideo> programvideos = programvideoRepository.findAll();
        return programvideos;
    }

    /**
     * GET  /programvideos/:id : get the "id" programvideo.
     *
     * @param id the id of the programvideo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the programvideo, or with status 404 (Not Found)
     */
    @GetMapping("/programvideos/{id}")
    public ResponseEntity<ProgramVideo> getProgramVideo(@PathVariable Long id) {
        log.debug("REST request to get ProgramVideo : {}", id);
        ProgramVideo programvideo = programvideoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(programvideo));
    }

    /**
     * DELETE  /programvideos/:id : delete the "id" programvideo.
     *
     * @param id the id of the programvideo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/programvideos/{id}")
    public ResponseEntity<Void> deleteProgramVideo(@PathVariable Long id) {
        log.debug("REST request to delete ProgramVideo : {}", id);
        programvideoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}