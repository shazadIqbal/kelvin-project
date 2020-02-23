package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ApplicantForm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ApplicantForm}.
 */
public interface ApplicantFormService {

    /**
     * Save a applicantForm.
     *
     * @param applicantForm the entity to save.
     * @return the persisted entity.
     */
    ApplicantForm save(ApplicantForm applicantForm);

    /**
     * Get all the applicantForms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ApplicantForm> findAll(Pageable pageable);

    /**
     * Get the "id" applicantForm.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApplicantForm> findOne(Long id);

    /**
     * Delete the "id" applicantForm.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
