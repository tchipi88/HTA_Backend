package com.tsoft.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tsoft.app.domain.Patient;
import com.tsoft.app.domain.PatientBilan;
import com.tsoft.app.repository.PatientBilanRepository;
import com.tsoft.app.service.PatientService;
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
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing PatientBilan.
 */
@RestController
@RequestMapping("/api")
public class PatientBilanResource {

    private final Logger log = LoggerFactory.getLogger(PatientBilanResource.class);

    private static final String ENTITY_NAME = "patientBilan";

    private final PatientBilanRepository patientBilanRepository;

    private final PatientService patientService;

    public PatientBilanResource(PatientBilanRepository patientBilanRepository, PatientService patientService) {
        this.patientBilanRepository = patientBilanRepository;
        this.patientService = patientService;
    }

    /**
     * POST /patient-bilans : Create a new patientBilan.
     *
     * @param patientBilan the patientBilan to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new patientBilan, or with status 400 (Bad Request) if the patientBilan
     * has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/patient-bilans")
    @Timed
    public ResponseEntity<PatientBilan> createPatientBilan(@Valid @RequestBody PatientBilan patientBilan) throws Exception {
        log.debug("REST request to save PatientBilan : {}", patientBilan);
        if (patientBilan.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new patientBilan cannot already have an ID")).body(null);
        }
        Patient patient = patientBilan.getPatient();
        if (patient.isBilanChecked()) {
            patient.setDateLastBilan(LocalDate.now());
        } else {
            patient.setDateFirstBilan(LocalDate.now());
            patient.setBilanChecked(true);
            patient.setDateLastBilan(LocalDate.now());
        }
        patientService.updatePatient(patient);
        PatientBilan result = patientBilanRepository.save(patientBilan);
        return ResponseEntity.created(new URI("/api/patient-bilans/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT /patient-bilans : Updates an existing patientBilan.
     *
     * @param patientBilan the patientBilan to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     * patientBilan, or with status 400 (Bad Request) if the patientBilan is not
     * valid, or with status 500 (Internal Server Error) if the patientBilan
     * couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/patient-bilans")
    @Timed
    public ResponseEntity<PatientBilan> updatePatientBilan(@Valid @RequestBody PatientBilan patientBilan) throws Exception {
        log.debug("REST request to update PatientBilan : {}", patientBilan);
        if (patientBilan.getId() == null) {
            return createPatientBilan(patientBilan);
        }
        PatientBilan result = patientBilanRepository.save(patientBilan);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, patientBilan.getId().toString()))
                .body(result);
    }

    /**
     * GET /patient-bilans : get all the patientBilans.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of
     * patientBilans in body
     */
    @GetMapping("/patient-bilans")
    @Timed
    public ResponseEntity<List<PatientBilan>> getAllPatientBilans(@ApiParam Pageable pageable) {
        log.debug("REST request to get all PatientBilans");
        Page<PatientBilan> page = patientBilanRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/patient-bilans");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET /patient-bilans/:id : get the "id" patientBilan.
     *
     * @param id the id of the patientBilan to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the
     * patientBilan, or with status 404 (Not Found)
     */
    @GetMapping("/patient-bilans/{id}")
    @Timed
    public ResponseEntity<PatientBilan> getPatientBilan(@PathVariable Long id) {
        log.debug("REST request to get PatientBilan : {}", id);
        PatientBilan patientBilan = patientBilanRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(patientBilan));
    }

    /**
     * DELETE /patient-bilans/:id : delete the "id" patientBilan.
     *
     * @param id the id of the patientBilan to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/patient-bilans/{id}")
    @Timed
    public ResponseEntity<Void> deletePatientBilan(@PathVariable Long id) {
        log.debug("REST request to delete PatientBilan : {}", id);
        patientBilanRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
