package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ApplicantFormService;
import com.mycompany.myapp.domain.ApplicantForm;
import com.mycompany.myapp.repository.ApplicantFormRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ApplicantForm}.
 */
@Service
@Transactional
public class ApplicantFormServiceImpl implements ApplicantFormService {

    private final Logger log = LoggerFactory.getLogger(ApplicantFormServiceImpl.class);

    private final ApplicantFormRepository applicantFormRepository;

    public ApplicantFormServiceImpl(ApplicantFormRepository applicantFormRepository) {
        this.applicantFormRepository = applicantFormRepository;
    }

    /**
     * Save a applicantForm.
     *
     * @param applicantForm the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ApplicantForm save(ApplicantForm applicantForm) {
        log.debug("Request to save ApplicantForm : {}", applicantForm);
        return applicantFormRepository.save(applicantForm);
    }

    /**
     * Get all the applicantForms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ApplicantForm> findAll(Pageable pageable) {
        log.debug("Request to get all ApplicantForms");
        return applicantFormRepository.findAll(pageable);
    }

    /**
     * Get one applicantForm by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ApplicantForm> findOne(Long id) {
        log.debug("Request to get ApplicantForm : {}", id);
        return applicantFormRepository.findById(id);
    }

    /**
     * Delete the applicantForm by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApplicantForm : {}", id);
        applicantFormRepository.deleteById(id);
    }
}
