package com.tsoft.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tsoft.app.domain.Patient;
import com.tsoft.app.domain.PatientBloodplessure;
import com.tsoft.app.repository.PatientBloodplessureRepository;
import com.tsoft.app.repository.PatientRepository;
import com.tsoft.app.web.rest.util.HeaderUtil;
import com.tsoft.app.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing PatientBloodplessure.
 */
@RestController
@RequestMapping("/api")
public class PatientBloodplessureResource {

    private final Logger log = LoggerFactory.getLogger(PatientBloodplessureResource.class);

    private static final String ENTITY_NAME = "patientBloodplessure";

    private final PatientBloodplessureRepository patientBloodplessureRepository;
    private final PatientRepository patientRepository;

    public PatientBloodplessureResource(PatientBloodplessureRepository patientBloodplessureRepository,
            PatientRepository patientRepository) {
        this.patientBloodplessureRepository = patientBloodplessureRepository;
        this.patientRepository = patientRepository;
    }

    /**
     * POST /patient-bloodplessures : Create a new patientBloodplessure.
     *
     * @param patientBloodplessure the patientBloodplessure to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new patientBloodplessure, or with status 400 (Bad Request) if the
     * patientBloodplessure has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/patient-bloodplessures")
    @Timed
    @Transactional
    public ResponseEntity<PatientBloodplessure> createPatientBloodplessure(@Valid @RequestBody PatientBloodplessure patientBloodplessure) throws Exception {
        log.debug("REST request to save PatientBloodplessure : {}", patientBloodplessure);
        if (patientBloodplessure.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new patientBloodplessure cannot already have an ID")).body(null);
        }
        Patient patient = patientBloodplessure.getPatient();
        patient.setPaDiastolique(patientBloodplessure.getPaDiastolique());
        patient.setPaSystolique(patientBloodplessure.getPaSystolique());
        patient.setWeight(patientBloodplessure.getWeight());

        patientRepository.save(patient);

        PatientBloodplessure result = patientBloodplessureRepository.save(patientBloodplessure);
        return ResponseEntity.created(new URI("/api/patient-bloodplessures/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * GET /patient-bloodplessures : get all the patientBloodplessures.
     *
     * @param fromDate
     * @param toDate
     * @param pageable
     * @return the ResponseEntity with status 200 (OK) and the list of patientBloodplessures in body
     */
    @GetMapping(path = "/patient-bloodplessures", params = {"fromDate", "toDate"})
    @Timed
    public ResponseEntity<List<PatientBloodplessure>> getAllPatientBloodplessuresByDate(
            @RequestParam(value = "fromDate") LocalDate fromDate,
            @RequestParam(value = "toDate") LocalDate toDate,
            @ApiParam Pageable pageable) {
        log.debug("REST request to get all PatientBloodplessures");
        Page<PatientBloodplessure> page = patientBloodplessureRepository.findAllByDateReleveBetweenOrderByDateReleveAsc(fromDate, toDate, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/patient-bloodplessures");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET /patient-bloodplessures/:id : get the "id" patientBloodplessure.
     *
     * @param id the id of the patientBloodplessure to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the
     * patientBloodplessure, or with status 404 (Not Found)
     */
    @GetMapping("/patient-bloodplessures/{id}")
    @Timed
    public ResponseEntity<PatientBloodplessure> getPatientBloodplessure(@PathVariable Long id) {
        log.debug("REST request to get PatientBloodplessure : {}", id);
        PatientBloodplessure patientBloodplessure = patientBloodplessureRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(patientBloodplessure));
    }

    /**
     * DELETE /patient-bloodplessures/:id : delete the "id"
     * patientBloodplessure.
     *
     * @param id the id of the patientBloodplessure to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/patient-bloodplessures/{id}")
    @Timed
    public ResponseEntity<Void> deletePatientBloodplessure(@PathVariable Long id) {
        log.debug("REST request to delete PatientBloodplessure : {}", id);
        patientBloodplessureRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
