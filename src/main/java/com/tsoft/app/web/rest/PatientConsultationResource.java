package com.tsoft.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tsoft.app.domain.Patient;
import com.tsoft.app.domain.PatientConsultation;
import com.tsoft.app.repository.PatientConsultationRepository;
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
 * REST controller for managing PatientConsultation.
 */
@RestController
@RequestMapping("/api")
public class PatientConsultationResource {

    private final Logger log = LoggerFactory.getLogger(PatientConsultationResource.class);

    private static final String ENTITY_NAME = "patientConsultation";

    private final PatientConsultationRepository patientConsultationRepository;

    private final PatientService patientService;

    public PatientConsultationResource(PatientConsultationRepository patientConsultationRepository, PatientService patientService) {
        this.patientConsultationRepository = patientConsultationRepository;
        this.patientService = patientService;
    }

    /**
     * POST /patient-consultations : Create a new patientConsultation.
     *
     * @param patientConsultation the patientConsultation to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new patientConsultation, or with status 400 (Bad Request) if the
     * patientConsultation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/patient-consultations")
    @Timed
    public ResponseEntity<PatientConsultation> createPatientConsultation(@Valid @RequestBody PatientConsultation patientConsultation) throws Exception {
        log.debug("REST request to save PatientConsultation : {}", patientConsultation);
        if (patientConsultation.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new patientConsultation cannot already have an ID")).body(null);
        }
        Patient patient = patientConsultation.getPatient();
        patient.setConsulte(true);
        patient.setDateNextConsultation(patientConsultation.getDateNextConsultation());
        patient.setDiagnosticHypertension(patientConsultation.isDiagnosticHypertension());
        patient.setDateLastConsultation(LocalDate.now());
        patient.setDateLastBloodPressureMesured(LocalDate.now());
        patient.setPaDiastolique(patientConsultation.getPaDiastolique());
        patient.setPaSystolique(patientConsultation.getPaSystolique());
        patientService.updatePatient(patient);

        PatientConsultation result = patientConsultationRepository.save(patientConsultation);
        return ResponseEntity.created(new URI("/api/patient-consultations/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT /patient-consultations : Updates an existing patientConsultation.
     *
     * @param patientConsultation the patientConsultation to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     * patientConsultation, or with status 400 (Bad Request) if the
     * patientConsultation is not valid, or with status 500 (Internal Server
     * Error) if the patientConsultation couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/patient-consultations")
    @Timed
    public ResponseEntity<PatientConsultation> updatePatientConsultation(@Valid @RequestBody PatientConsultation patientConsultation) throws Exception {
        log.debug("REST request to update PatientConsultation : {}", patientConsultation);
        if (patientConsultation.getId() == null) {
            return createPatientConsultation(patientConsultation);
        }
        PatientConsultation result = patientConsultationRepository.save(patientConsultation);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, patientConsultation.getId().toString()))
                .body(result);
    }

    /**
     * GET /patient-consultations : get all the patientConsultations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of
     * patientConsultations in body
     */
    @GetMapping("/patient-consultations")
    @Timed
    public ResponseEntity<List<PatientConsultation>> getAllPatientConsultations(@ApiParam Pageable pageable) {
        log.debug("REST request to get all PatientConsultations");
        Page<PatientConsultation> page = patientConsultationRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/patient-consultations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET /patient-consultations/:id : get the "id" patientConsultation.
     *
     * @param patientId
     * @return the ResponseEntity with status 200 (OK) and with body the
     * patientConsultation, or with status 404 (Not Found)
     */
    @GetMapping("/patient-consultations/{patientId}")
    @Timed
    public ResponseEntity<PatientConsultation> getPatientConsultation(@PathVariable Long patientId) {
        log.debug("REST request to get PatientConsultation : {}", patientId);
        PatientConsultation patientConsultation = patientConsultationRepository.findFirstByPatientIdOrderByDateConsultationDesc(patientId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(patientConsultation));
    }

    /**
     * DELETE /patient-consultations/:id : delete the "id" patientConsultation.
     *
     * @param id the id of the patientConsultation to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/patient-consultations/{id}")
    @Timed
    public ResponseEntity<Void> deletePatientConsultation(@PathVariable Long id) {
        log.debug("REST request to delete PatientConsultation : {}", id);
        patientConsultationRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
