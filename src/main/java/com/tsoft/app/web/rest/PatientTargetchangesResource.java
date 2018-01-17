package com.tsoft.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tsoft.app.domain.Patient;
import com.tsoft.app.domain.PatientTargetchanges;
import com.tsoft.app.repository.PatientTargetchangesRepository;
import com.tsoft.app.service.PatientService;
import com.tsoft.app.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing PatientTargetchanges.
 */
@RestController
@RequestMapping("/api")
public class PatientTargetchangesResource {

    private final Logger log = LoggerFactory.getLogger(PatientTargetchangesResource.class);

    private static final String ENTITY_NAME = "patientTargetchanges";

    private final PatientTargetchangesRepository patientTargetchangesRepository;

    private final PatientService patientService;

    public PatientTargetchangesResource(PatientTargetchangesRepository patientTargetchangesRepository, PatientService patientService) {
        this.patientTargetchangesRepository = patientTargetchangesRepository;
        this.patientService = patientService;
    }

    /**
     * POST /patient-targetchangess : Create a new patientTargetchanges.
     *
     * @param patientTargetchanges the patientTargetchanges to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new patientTargetchanges, or with status 400 (Bad Request) if the
     * patientTargetchanges has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/patient-targetchangess")
    @Timed
    @Transactional
    public ResponseEntity<PatientTargetchanges> createPatientTargetchanges(@Valid @RequestBody PatientTargetchanges patientTargetchanges)
            throws Exception {
        log.debug("REST request to save PatientTargetchanges : {}", patientTargetchanges);
        if (patientTargetchanges.getId() != null) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new patientTargetchanges cannot already have an ID"))
                    .body(null);
        }
        Patient patient = patientTargetchanges.getPatient();
        patient.setDefineTarget(true);
        patient.setDateLastDefineTarget(LocalDate.now());

        patientService.updatePatient(patient);

        PatientTargetchanges result = patientTargetchangesRepository.save(patientTargetchanges);
        return ResponseEntity.created(new URI("/api/patient-targetchangess/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * GET /patient-targetchangess/:id : get the "id" patientTargetchanges.
     *
     * @param patientId
     * @return the ResponseEntity with status 200 (OK) and with body the patientTargetchanges, or
     *         with status 404 (Not Found)
     */
    @GetMapping("/patient-targetchangess/{patientId}")
    @Timed
    public ResponseEntity<PatientTargetchanges> getPatientTargetchanges(@PathVariable Long patientId) {
        log.debug("REST request to get PatientTargetchanges : {}", patientId);
        PatientTargetchanges patientTargetchanges = patientTargetchangesRepository.findFirstByPatientIdOrderByDateTargetDesc(patientId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(patientTargetchanges));
    }

    /**
     * DELETE /patient-targetchangess/:id : delete the "id"
     * patientTargetchanges.
     *
     * @param id the id of the patientTargetchanges to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/patient-targetchangess/{id}")
    @Timed
    public ResponseEntity<Void> deletePatientTargetchanges(@PathVariable Long id) {
        log.debug("REST request to delete PatientTargetchanges : {}", id);
        patientTargetchangesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
