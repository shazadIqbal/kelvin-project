package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ApplicantForm;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ApplicantForm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApplicantFormRepository extends JpaRepository<ApplicantForm, Long> {

}
