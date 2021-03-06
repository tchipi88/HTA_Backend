package com.tsoft.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tsoft.app.domain.Patient;
import com.tsoft.app.repository.PatientRepository;
import com.tsoft.app.repository.search.PatientSearchRepository;
import com.tsoft.app.service.PatientService;
import com.tsoft.app.web.rest.util.HeaderUtil;
import com.tsoft.app.web.rest.util.PaginationUtil;
import com.tsoft.app.web.rest.vm.Dashboard;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Optional;
import static org.elasticsearch.index.query.QueryBuilders.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing Patient.
 */
@RestController
@RequestMapping("/api")
public class PatientResource {

    private final Logger log = LoggerFactory.getLogger(PatientResource.class);

    private static final String ENTITY_NAME = "patient";

    private final PatientRepository patientRepository;

    private final PatientService patientService;

    private final PatientSearchRepository patientSearchRepository;

    public PatientResource(PatientRepository patientRepository, PatientService patientService,
            PatientSearchRepository patientSearchRepository) {
        this.patientRepository = patientRepository;
        this.patientSearchRepository = patientSearchRepository;
        this.patientService = patientService;
    }

    /**
     * POST /patients : Create a new patient.
     *
     * @param patient the patient to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new patient, or with status 400 (Bad Request) if the patient has already
     * an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/patients")
    @Timed
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) throws Exception {
        log.debug("REST request to save Patient : {}", patient);
        if (patient.getId() != null) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new patient cannot already have an ID")).body(null);
        }

        Patient result = patientService.createPatient(patient);
        patientSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/patients/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * PUT /patients : Updates an existing patient.
     *
     * @param patient the patient to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     * patient, or with status 400 (Bad Request) if the patient is not valid, or
     * with status 500 (Internal Server Error) if the patient couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/patients")
    @Timed
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) throws Exception {
        log.debug("REST request to update Patient : {}", patient);
        if (patient.getId() == null) {
            return createPatient(patient);
        }
        Patient result = patientService.updatePatient(patient);
        patientSearchRepository.save(result);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, patient.getId().toString())).body(result);
    }

    /**
     * GET /patients : get all the patients.
     *
     * @param query
     * @param filter
     * @param pageable
     * @return the ResponseEntity with status 200 (OK) and the list of patients
     * in body
     */
    @GetMapping("/patients")
    @Timed
    public ResponseEntity<Page<Patient>> getAllPatients(@RequestParam(name = "query", required = false) String query,
            @ApiParam Pageable pageable) {
        log.debug("REST request to get all Patients with query  {} and fliter {}", query);
        return new ResponseEntity<>(patientService.getPatients(query, pageable), HttpStatus.OK);
    }

 /**
     * SEARCH /_search/patients?query=:query : search for the patient
     * corresponding to the query.
     *
     * @param query the query of the patient search
     * @return the result of the search
     */
    @GetMapping("/patientss")
    @Timed
    public ResponseEntity<Page<Patient>> searchPatients(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search Patients for query {}", query);
        Page<Patient> page = patientSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/patientss");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
    
    /**
     * GET /patients/:id : get the "id" patient.
     *
     * @param id the id of the patient to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the
     * patient, or with status 404 (Not Found)
     */
    @GetMapping("/patients/{id}")
    @Timed
    public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
        log.debug("REST request to get Patient : {}", id);
        Patient patient = patientRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(patient));
    }

    /**
     * DELETE /patients/:id : delete the "id" patient.
     *
     * @param id the id of the patient to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/patients/{id}")
    @Timed
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        log.debug("REST request to delete Patient : {}", id);
        patientRepository.delete(id);
        patientSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

   

    @GetMapping(path = "/patients-dashboard", params = {"fromDate", "toDate"})
    @Timed
    public ResponseEntity<Dashboard> getDashboard(@RequestParam(value = "fromDate") LocalDate fromDate,
            @RequestParam(value = "toDate") LocalDate toDate) {
        Dashboard dashboard = patientService.getDashboard(fromDate, toDate);
        return new ResponseEntity<>(dashboard, HttpStatus.OK);

    }

    @GetMapping(path = "/patients-dashboardM")
    @Timed
    public ResponseEntity<Dashboard> getDashboardMedecin() {
        Dashboard dashboard = patientService.getDashboardMedecin();
        return new ResponseEntity<>(dashboard, HttpStatus.OK);

    }

}
