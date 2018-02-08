package com.tsoft.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tsoft.app.domain.Patient;
import com.tsoft.app.domain.PatientBloodplessure;
import com.tsoft.app.repository.PatientBloodplessureRepository;
import com.tsoft.app.service.PatientService;
import com.tsoft.app.web.rest.util.HeaderUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final PatientService patientService;

    public PatientBloodplessureResource(PatientBloodplessureRepository patientBloodplessureRepository, PatientService patientService) {
        this.patientBloodplessureRepository = patientBloodplessureRepository;
        this.patientService = patientService;
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
        patient.setBloodplessureMesured(true);
        patient.setDateLastBloodplessureMesured(LocalDate.now());
        patient.setBloodplessureTomesure(!(patientBloodplessure.getPaSystolique() < 120 && patientBloodplessureRepository.countByPatientAndDateReleveAfter(patientBloodplessure.getPatient(), LocalDate.now().minusMonths(2)) > 2));
        patientService.updatePatient(patient);

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
     * @param patientId
     * @return the ResponseEntity with status 200 (OK) and the list of
     * patientBloodplessures in body
     */
    @GetMapping(path = "/patient-bloodplessures/{patientId}", params = {"fromDate", "toDate"})
    @Timed
    public ResponseEntity<List<PatientBloodplessure>> getAllPatientBloodplessuresByDate(
            @RequestParam(value = "fromDate") LocalDate fromDate, @RequestParam(value = "toDate") LocalDate toDate,
            @PathVariable Long patientId) {
        log.debug("REST request to get all PatientBloodplessures");
        List<PatientBloodplessure> page = patientBloodplessureRepository
                .findAllByPatientIdAndDateReleveBetweenOrderByDateReleveAsc(patientId, fromDate, toDate.plusDays(1));
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping(path = "/patient-bloodplessures/{patientId}")
    @Timed
    public ResponseEntity<List<PatientBloodplessure>> getAllPatientBloodplessuresByDate(@PathVariable Long patientId) {
        log.debug("REST request to get all PatientBloodplessures");
        LocalDate fromDate = LocalDate.now().minusMonths(3);
        LocalDate toDate = LocalDate.now().plusDays(1);
        List<PatientBloodplessure> page = patientBloodplessureRepository
                .findAllByPatientIdAndDateReleveBetweenOrderByDateReleveAsc(patientId, fromDate, toDate);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

}
