package com.tsoft.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tsoft.app.domain.Patient;
import com.tsoft.app.domain.PatientTreatment;
import com.tsoft.app.repository.PatientTreatmentRepository;
import com.tsoft.app.service.PatientService;
import com.tsoft.app.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing PatientTreatment.
 */
@RestController
@RequestMapping("/api")
public class PatientTreatmentResource {

    private final Logger log = LoggerFactory.getLogger(PatientTreatmentResource.class);

    private static final String ENTITY_NAME = "patientTreatment";

    private final PatientTreatmentRepository patientTreatmentRepository;

    private final PatientService patientService;

    public PatientTreatmentResource(PatientTreatmentRepository patientTreatmentRepository, PatientService patientService) {
        this.patientTreatmentRepository = patientTreatmentRepository;
        this.patientService = patientService;
    }

    /**
     * POST /patient-treatments : Create a new patientTreatment.
     *
     * @param patientTreatment the patientTreatment to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new patientTreatment, or with status 400 (Bad Request) if the
     * patientTreatment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/patient-treatments")
    @Timed
    public ResponseEntity<PatientTreatment> createPatientTreatment(@Valid @RequestBody PatientTreatment patientTreatment) throws Exception {
        log.debug("REST request to save PatientTreatment : {}", patientTreatment);
        if (patientTreatment.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new patientTreatment cannot already have an ID")).body(null);
        }
        Patient patient = patientTreatment.getPatient();
        patient.setMonitoringTreatement(true);
        patientService.updatePatient(patient);

        PatientTreatment result = patientTreatmentRepository.save(patientTreatment);
        return ResponseEntity.created(new URI("/api/patient-treatments/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * GET /patient-treatments : get all the patientTreatments.
     *
     * @param patientId
     * @return the ResponseEntity with status 200 (OK) and the list of
     * patientTreatments in body
     */
    @GetMapping("/patient-treatments/{patientId}")
    @Timed
    public ResponseEntity<List<PatientTreatment>> getAllPatientTreatments(@PathVariable Long patientId) {
        log.debug("REST request to get all PatientTreatments");
        LocalDate fromDate = LocalDate.now().minusMonths(3);
        LocalDate toDate = LocalDate.now().plusDays(1);
        List<PatientTreatment> page = patientTreatmentRepository.findAllByPatientIdAndDateReleveBetweenOrderByDateReleveAsc(patientId, fromDate, toDate);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    /**
     * GET /patient-treatments/:id : get the "id" patientTreatment.
     *
     * @param patientId
     * @return the ResponseEntity with status 200 (OK) and with body the
     * patientTreatment, or with status 404 (Not Found)
     */
    @GetMapping("/patient-treatmentss/{patientId}")
    @Timed
    public ResponseEntity<PatientTreatment> getLastPatientTreatment(@PathVariable Long patientId) {
        log.debug("REST request to get PatientTreatment : {}", patientId);
        PatientTreatment patientTreatment = patientTreatmentRepository.findFirstByPatientIdOrderByDateReleveDesc(patientId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(patientTreatment));
    }


}
