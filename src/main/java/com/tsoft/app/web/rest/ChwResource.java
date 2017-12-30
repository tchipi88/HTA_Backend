package com.tsoft.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tsoft.app.domain.Chw;
import com.tsoft.app.repository.ChwRepository;
import com.tsoft.app.service.ChwService;
import com.tsoft.app.web.rest.util.HeaderUtil;
import com.tsoft.app.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing Chw.
 */
@RestController
@RequestMapping("/api")
public class ChwResource {

    final Logger log = LoggerFactory.getLogger(ChwResource.class);

    private static final String ENTITY_NAME = "chw";

    private final ChwRepository chwRepository;

    private final ChwService chwService;

    public ChwResource(ChwRepository chwRepository, ChwService chwService) {
        this.chwRepository = chwRepository;
        this.chwService = chwService;
    }

    /**
     * POST /chws : Create a new chw.
     *
     * @param chw the chw to create
     * @return the ResponseEntity with status 201 (Created) and with body the new chw, or with
     *         status 400 (Bad Request) if the chw has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/chws")
    @Timed
    public ResponseEntity<Chw> createChw(@RequestBody Chw chw) throws Exception {
        log.debug("REST request to save Chw : {}", chw);
        if (chw.getId() != null) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new chw cannot already have an ID")).body(null);
        }
        if (chwRepository.findOneByEmail(chw.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "emailexists", "Email already in use"))
                    .body(null);
        }
        Chw result = chwService.createChw(chw);
        return ResponseEntity.created(new URI("/api/chws/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * PUT /chws : Updates an existing chw.
     *
     * @param chw the chw to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated chw, or with status
     *         400 (Bad Request) if the chw is not valid, or with status 500 (Internal Server Error)
     *         if the chw couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/chws")
    @Timed
    public ResponseEntity<Chw> updateChw(@RequestBody Chw chw) throws Exception {
        log.debug("REST request to update Chw : {}", chw);
        if (chw.getId() == null) {
            return createChw(chw);
        }
        Optional<Chw> existingChw = chwRepository.findOneByEmail(chw.getEmail());
        if (existingChw.isPresent() && (!existingChw.get().getId().equals(chw.getId()))) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "emailexists", "E-mail already in use"))
                    .body(null);
        }
        Chw result = chwService.updateChw(chw);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, chw.getId().toString())).body(result);
    }

    /**
     * GET /chws : get all the chws.
     *
     * @param pageable
     * @return the ResponseEntity with status 200 (OK) and the list of chws in body
     */
    @GetMapping("/chws")
    @Timed
    public ResponseEntity<Page<Chw>> getAllChws(@ApiParam Pageable pageable) {
        log.debug("REST request to get all Chws");
        Page<Chw> page = chwRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/chws");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /**
     * GET /chws/:id : get the "id" chw.
     *
     * @param id the id of the chw to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the chw, or with status 404
     *         (Not Found)
     */
    @GetMapping("/chws/{id}")
    @Timed
    public ResponseEntity<Chw> getChw(@PathVariable Long id) {
        log.debug("REST request to get Chw : {}", id);
        Chw chw = chwRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(chw));
    }

    /**
     * DELETE /chws/:id : delete the "id" chw.
     *
     * @param id the id of the chw to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/chws/{id}")
    @Timed
    public ResponseEntity<Void> deleteChw(@PathVariable Long id) {
        log.debug("REST request to delete Chw : {}", id);
        chwRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
