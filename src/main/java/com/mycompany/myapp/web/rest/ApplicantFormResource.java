package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ApplicantForm;
import com.mycompany.myapp.service.ApplicantFormService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.ApplicantForm}.
 */
@RestController
@RequestMapping("/api")
public class ApplicantFormResource {

    private final Logger log = LoggerFactory.getLogger(ApplicantFormResource.class);

    private static final String ENTITY_NAME = "applicantForm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApplicantFormService applicantFormService;

    public ApplicantFormResource(ApplicantFormService applicantFormService) {
        this.applicantFormService = applicantFormService;
    }

    /**
     * {@code POST  /applicant-forms} : Create a new applicantForm.
     *
     * @param applicantForm the applicantForm to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new applicantForm, or with status {@code 400 (Bad Request)} if the applicantForm has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/applicant-forms")
    public ResponseEntity<ApplicantForm> createApplicantForm(@RequestBody ApplicantForm applicantForm) throws URISyntaxException {
        log.debug("REST request to save ApplicantForm : {}", applicantForm);
        if (applicantForm.getId() != null) {
            throw new BadRequestAlertException("A new applicantForm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApplicantForm result = applicantFormService.save(applicantForm);
        return ResponseEntity.created(new URI("/api/applicant-forms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /applicant-forms} : Updates an existing applicantForm.
     *
     * @param applicantForm the applicantForm to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated applicantForm,
     * or with status {@code 400 (Bad Request)} if the applicantForm is not valid,
     * or with status {@code 500 (Internal Server Error)} if the applicantForm couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/applicant-forms")
    public ResponseEntity<ApplicantForm> updateApplicantForm(@RequestBody ApplicantForm applicantForm) throws URISyntaxException {
        log.debug("REST request to update ApplicantForm : {}", applicantForm);
        if (applicantForm.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApplicantForm result = applicantFormService.save(applicantForm);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, applicantForm.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /applicant-forms} : get all the applicantForms.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of applicantForms in body.
     */
    @GetMapping("/applicant-forms")
    public ResponseEntity<List<ApplicantForm>> getAllApplicantForms(Pageable pageable) {
        log.debug("REST request to get a page of ApplicantForms");
        Page<ApplicantForm> page = applicantFormService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /applicant-forms/:id} : get the "id" applicantForm.
     *
     * @param id the id of the applicantForm to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the applicantForm, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/applicant-forms/{id}")
    public ResponseEntity<ApplicantForm> getApplicantForm(@PathVariable Long id) {
        log.debug("REST request to get ApplicantForm : {}", id);
        Optional<ApplicantForm> applicantForm = applicantFormService.findOne(id);
        return ResponseUtil.wrapOrNotFound(applicantForm);
    }

    /**
     * {@code DELETE  /applicant-forms/:id} : delete the "id" applicantForm.
     *
     * @param id the id of the applicantForm to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/applicant-forms/{id}")
    public ResponseEntity<Void> deleteApplicantForm(@PathVariable Long id) {
        log.debug("REST request to delete ApplicantForm : {}", id);
        applicantFormService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
