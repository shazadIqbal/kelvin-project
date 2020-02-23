package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.ApplicantForm;
import com.mycompany.myapp.repository.ApplicantFormRepository;
import com.mycompany.myapp.service.ApplicantFormService;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ApplicantFormResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class ApplicantFormResourceIT {

    private static final byte[] DEFAULT_USER_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_USER_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_USER_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_USER_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_RECEVIER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_RECEVIER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_PLACE_OF_BIRTH = "AAAAAAAAAA";
    private static final String UPDATED_PLACE_OF_BIRTH = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_OF_BIRTH = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_OF_BIRTH = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_VISA_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_VISA_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_CITIZENSHIP = "AAAAAAAAAA";
    private static final String UPDATED_CITIZENSHIP = "BBBBBBBBBB";

    private static final byte[] DEFAULT_RESUME = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_RESUME = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_RESUME_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_RESUME_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_EMPLOYEE_APPLICATION = false;
    private static final Boolean UPDATED_EMPLOYEE_APPLICATION = true;

    private static final Boolean DEFAULT_EMPLOYEE_ORIENTATION = false;
    private static final Boolean UPDATED_EMPLOYEE_ORIENTATION = true;

    private static final Boolean DEFAULT_EMPLOYEE_IDENTIFICATION = false;
    private static final Boolean UPDATED_EMPLOYEE_IDENTIFICATION = true;

    private static final Boolean DEFAULT_SECURITY_CLEARANCE = false;
    private static final Boolean UPDATED_SECURITY_CLEARANCE = true;

    private static final Boolean DEFAULT_MEDICAL_CLEARANCE = false;
    private static final Boolean UPDATED_MEDICAL_CLEARANCE = true;

    private static final Boolean DEFAULT_EMPLOYEE_WELLNESS = false;
    private static final Boolean UPDATED_EMPLOYEE_WELLNESS = true;

    private static final Boolean DEFAULT_EMERGENCY_CONTACT = false;
    private static final Boolean UPDATED_EMERGENCY_CONTACT = true;

    @Autowired
    private ApplicantFormRepository applicantFormRepository;

    @Autowired
    private ApplicantFormService applicantFormService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restApplicantFormMockMvc;

    private ApplicantForm applicantForm;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ApplicantFormResource applicantFormResource = new ApplicantFormResource(applicantFormService);
        this.restApplicantFormMockMvc = MockMvcBuilders.standaloneSetup(applicantFormResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApplicantForm createEntity(EntityManager em) {
        ApplicantForm applicantForm = new ApplicantForm()
            .userImage(DEFAULT_USER_IMAGE)
            .userImageContentType(DEFAULT_USER_IMAGE_CONTENT_TYPE)
            .name(DEFAULT_NAME)
            .email(DEFAULT_EMAIL)
            .recevierEmail(DEFAULT_RECEVIER_EMAIL)
            .phone(DEFAULT_PHONE)
            .address(DEFAULT_ADDRESS)
            .gender(DEFAULT_GENDER)
            .placeOfBirth(DEFAULT_PLACE_OF_BIRTH)
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .visaDetails(DEFAULT_VISA_DETAILS)
            .citizenship(DEFAULT_CITIZENSHIP)
            .resume(DEFAULT_RESUME)
            .resumeContentType(DEFAULT_RESUME_CONTENT_TYPE)
            .employeeApplication(DEFAULT_EMPLOYEE_APPLICATION)
            .employeeOrientation(DEFAULT_EMPLOYEE_ORIENTATION)
            .employeeIdentification(DEFAULT_EMPLOYEE_IDENTIFICATION)
            .securityClearance(DEFAULT_SECURITY_CLEARANCE)
            .medicalClearance(DEFAULT_MEDICAL_CLEARANCE)
            .employeeWellness(DEFAULT_EMPLOYEE_WELLNESS)
            .emergencyContact(DEFAULT_EMERGENCY_CONTACT);
        return applicantForm;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApplicantForm createUpdatedEntity(EntityManager em) {
        ApplicantForm applicantForm = new ApplicantForm()
            .userImage(UPDATED_USER_IMAGE)
            .userImageContentType(UPDATED_USER_IMAGE_CONTENT_TYPE)
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .recevierEmail(UPDATED_RECEVIER_EMAIL)
            .phone(UPDATED_PHONE)
            .address(UPDATED_ADDRESS)
            .gender(UPDATED_GENDER)
            .placeOfBirth(UPDATED_PLACE_OF_BIRTH)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .visaDetails(UPDATED_VISA_DETAILS)
            .citizenship(UPDATED_CITIZENSHIP)
            .resume(UPDATED_RESUME)
            .resumeContentType(UPDATED_RESUME_CONTENT_TYPE)
            .employeeApplication(UPDATED_EMPLOYEE_APPLICATION)
            .employeeOrientation(UPDATED_EMPLOYEE_ORIENTATION)
            .employeeIdentification(UPDATED_EMPLOYEE_IDENTIFICATION)
            .securityClearance(UPDATED_SECURITY_CLEARANCE)
            .medicalClearance(UPDATED_MEDICAL_CLEARANCE)
            .employeeWellness(UPDATED_EMPLOYEE_WELLNESS)
            .emergencyContact(UPDATED_EMERGENCY_CONTACT);
        return applicantForm;
    }

    @BeforeEach
    public void initTest() {
        applicantForm = createEntity(em);
    }

    @Test
    @Transactional
    public void createApplicantForm() throws Exception {
        int databaseSizeBeforeCreate = applicantFormRepository.findAll().size();

        // Create the ApplicantForm
        restApplicantFormMockMvc.perform(post("/api/applicant-forms")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(applicantForm)))
            .andExpect(status().isCreated());

        // Validate the ApplicantForm in the database
        List<ApplicantForm> applicantFormList = applicantFormRepository.findAll();
        assertThat(applicantFormList).hasSize(databaseSizeBeforeCreate + 1);
        ApplicantForm testApplicantForm = applicantFormList.get(applicantFormList.size() - 1);
        assertThat(testApplicantForm.getUserImage()).isEqualTo(DEFAULT_USER_IMAGE);
        assertThat(testApplicantForm.getUserImageContentType()).isEqualTo(DEFAULT_USER_IMAGE_CONTENT_TYPE);
        assertThat(testApplicantForm.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testApplicantForm.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testApplicantForm.getRecevierEmail()).isEqualTo(DEFAULT_RECEVIER_EMAIL);
        assertThat(testApplicantForm.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testApplicantForm.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testApplicantForm.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testApplicantForm.getPlaceOfBirth()).isEqualTo(DEFAULT_PLACE_OF_BIRTH);
        assertThat(testApplicantForm.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testApplicantForm.getVisaDetails()).isEqualTo(DEFAULT_VISA_DETAILS);
        assertThat(testApplicantForm.getCitizenship()).isEqualTo(DEFAULT_CITIZENSHIP);
        assertThat(testApplicantForm.getResume()).isEqualTo(DEFAULT_RESUME);
        assertThat(testApplicantForm.getResumeContentType()).isEqualTo(DEFAULT_RESUME_CONTENT_TYPE);
        assertThat(testApplicantForm.isEmployeeApplication()).isEqualTo(DEFAULT_EMPLOYEE_APPLICATION);
        assertThat(testApplicantForm.isEmployeeOrientation()).isEqualTo(DEFAULT_EMPLOYEE_ORIENTATION);
        assertThat(testApplicantForm.isEmployeeIdentification()).isEqualTo(DEFAULT_EMPLOYEE_IDENTIFICATION);
        assertThat(testApplicantForm.isSecurityClearance()).isEqualTo(DEFAULT_SECURITY_CLEARANCE);
        assertThat(testApplicantForm.isMedicalClearance()).isEqualTo(DEFAULT_MEDICAL_CLEARANCE);
        assertThat(testApplicantForm.isEmployeeWellness()).isEqualTo(DEFAULT_EMPLOYEE_WELLNESS);
        assertThat(testApplicantForm.isEmergencyContact()).isEqualTo(DEFAULT_EMERGENCY_CONTACT);
    }

    @Test
    @Transactional
    public void createApplicantFormWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = applicantFormRepository.findAll().size();

        // Create the ApplicantForm with an existing ID
        applicantForm.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApplicantFormMockMvc.perform(post("/api/applicant-forms")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(applicantForm)))
            .andExpect(status().isBadRequest());

        // Validate the ApplicantForm in the database
        List<ApplicantForm> applicantFormList = applicantFormRepository.findAll();
        assertThat(applicantFormList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllApplicantForms() throws Exception {
        // Initialize the database
        applicantFormRepository.saveAndFlush(applicantForm);

        // Get all the applicantFormList
        restApplicantFormMockMvc.perform(get("/api/applicant-forms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(applicantForm.getId().intValue())))
            .andExpect(jsonPath("$.[*].userImageContentType").value(hasItem(DEFAULT_USER_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].userImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_USER_IMAGE))))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].recevierEmail").value(hasItem(DEFAULT_RECEVIER_EMAIL)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].placeOfBirth").value(hasItem(DEFAULT_PLACE_OF_BIRTH)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].visaDetails").value(hasItem(DEFAULT_VISA_DETAILS)))
            .andExpect(jsonPath("$.[*].citizenship").value(hasItem(DEFAULT_CITIZENSHIP)))
            .andExpect(jsonPath("$.[*].resumeContentType").value(hasItem(DEFAULT_RESUME_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].resume").value(hasItem(Base64Utils.encodeToString(DEFAULT_RESUME))))
            .andExpect(jsonPath("$.[*].employeeApplication").value(hasItem(DEFAULT_EMPLOYEE_APPLICATION.booleanValue())))
            .andExpect(jsonPath("$.[*].employeeOrientation").value(hasItem(DEFAULT_EMPLOYEE_ORIENTATION.booleanValue())))
            .andExpect(jsonPath("$.[*].employeeIdentification").value(hasItem(DEFAULT_EMPLOYEE_IDENTIFICATION.booleanValue())))
            .andExpect(jsonPath("$.[*].securityClearance").value(hasItem(DEFAULT_SECURITY_CLEARANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].medicalClearance").value(hasItem(DEFAULT_MEDICAL_CLEARANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].employeeWellness").value(hasItem(DEFAULT_EMPLOYEE_WELLNESS.booleanValue())))
            .andExpect(jsonPath("$.[*].emergencyContact").value(hasItem(DEFAULT_EMERGENCY_CONTACT.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getApplicantForm() throws Exception {
        // Initialize the database
        applicantFormRepository.saveAndFlush(applicantForm);

        // Get the applicantForm
        restApplicantFormMockMvc.perform(get("/api/applicant-forms/{id}", applicantForm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(applicantForm.getId().intValue()))
            .andExpect(jsonPath("$.userImageContentType").value(DEFAULT_USER_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.userImage").value(Base64Utils.encodeToString(DEFAULT_USER_IMAGE)))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.recevierEmail").value(DEFAULT_RECEVIER_EMAIL))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER))
            .andExpect(jsonPath("$.placeOfBirth").value(DEFAULT_PLACE_OF_BIRTH))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.visaDetails").value(DEFAULT_VISA_DETAILS))
            .andExpect(jsonPath("$.citizenship").value(DEFAULT_CITIZENSHIP))
            .andExpect(jsonPath("$.resumeContentType").value(DEFAULT_RESUME_CONTENT_TYPE))
            .andExpect(jsonPath("$.resume").value(Base64Utils.encodeToString(DEFAULT_RESUME)))
            .andExpect(jsonPath("$.employeeApplication").value(DEFAULT_EMPLOYEE_APPLICATION.booleanValue()))
            .andExpect(jsonPath("$.employeeOrientation").value(DEFAULT_EMPLOYEE_ORIENTATION.booleanValue()))
            .andExpect(jsonPath("$.employeeIdentification").value(DEFAULT_EMPLOYEE_IDENTIFICATION.booleanValue()))
            .andExpect(jsonPath("$.securityClearance").value(DEFAULT_SECURITY_CLEARANCE.booleanValue()))
            .andExpect(jsonPath("$.medicalClearance").value(DEFAULT_MEDICAL_CLEARANCE.booleanValue()))
            .andExpect(jsonPath("$.employeeWellness").value(DEFAULT_EMPLOYEE_WELLNESS.booleanValue()))
            .andExpect(jsonPath("$.emergencyContact").value(DEFAULT_EMERGENCY_CONTACT.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingApplicantForm() throws Exception {
        // Get the applicantForm
        restApplicantFormMockMvc.perform(get("/api/applicant-forms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApplicantForm() throws Exception {
        // Initialize the database
        applicantFormService.save(applicantForm);

        int databaseSizeBeforeUpdate = applicantFormRepository.findAll().size();

        // Update the applicantForm
        ApplicantForm updatedApplicantForm = applicantFormRepository.findById(applicantForm.getId()).get();
        // Disconnect from session so that the updates on updatedApplicantForm are not directly saved in db
        em.detach(updatedApplicantForm);
        updatedApplicantForm
            .userImage(UPDATED_USER_IMAGE)
            .userImageContentType(UPDATED_USER_IMAGE_CONTENT_TYPE)
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .recevierEmail(UPDATED_RECEVIER_EMAIL)
            .phone(UPDATED_PHONE)
            .address(UPDATED_ADDRESS)
            .gender(UPDATED_GENDER)
            .placeOfBirth(UPDATED_PLACE_OF_BIRTH)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .visaDetails(UPDATED_VISA_DETAILS)
            .citizenship(UPDATED_CITIZENSHIP)
            .resume(UPDATED_RESUME)
            .resumeContentType(UPDATED_RESUME_CONTENT_TYPE)
            .employeeApplication(UPDATED_EMPLOYEE_APPLICATION)
            .employeeOrientation(UPDATED_EMPLOYEE_ORIENTATION)
            .employeeIdentification(UPDATED_EMPLOYEE_IDENTIFICATION)
            .securityClearance(UPDATED_SECURITY_CLEARANCE)
            .medicalClearance(UPDATED_MEDICAL_CLEARANCE)
            .employeeWellness(UPDATED_EMPLOYEE_WELLNESS)
            .emergencyContact(UPDATED_EMERGENCY_CONTACT);

        restApplicantFormMockMvc.perform(put("/api/applicant-forms")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedApplicantForm)))
            .andExpect(status().isOk());

        // Validate the ApplicantForm in the database
        List<ApplicantForm> applicantFormList = applicantFormRepository.findAll();
        assertThat(applicantFormList).hasSize(databaseSizeBeforeUpdate);
        ApplicantForm testApplicantForm = applicantFormList.get(applicantFormList.size() - 1);
        assertThat(testApplicantForm.getUserImage()).isEqualTo(UPDATED_USER_IMAGE);
        assertThat(testApplicantForm.getUserImageContentType()).isEqualTo(UPDATED_USER_IMAGE_CONTENT_TYPE);
        assertThat(testApplicantForm.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testApplicantForm.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testApplicantForm.getRecevierEmail()).isEqualTo(UPDATED_RECEVIER_EMAIL);
        assertThat(testApplicantForm.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testApplicantForm.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testApplicantForm.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testApplicantForm.getPlaceOfBirth()).isEqualTo(UPDATED_PLACE_OF_BIRTH);
        assertThat(testApplicantForm.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testApplicantForm.getVisaDetails()).isEqualTo(UPDATED_VISA_DETAILS);
        assertThat(testApplicantForm.getCitizenship()).isEqualTo(UPDATED_CITIZENSHIP);
        assertThat(testApplicantForm.getResume()).isEqualTo(UPDATED_RESUME);
        assertThat(testApplicantForm.getResumeContentType()).isEqualTo(UPDATED_RESUME_CONTENT_TYPE);
        assertThat(testApplicantForm.isEmployeeApplication()).isEqualTo(UPDATED_EMPLOYEE_APPLICATION);
        assertThat(testApplicantForm.isEmployeeOrientation()).isEqualTo(UPDATED_EMPLOYEE_ORIENTATION);
        assertThat(testApplicantForm.isEmployeeIdentification()).isEqualTo(UPDATED_EMPLOYEE_IDENTIFICATION);
        assertThat(testApplicantForm.isSecurityClearance()).isEqualTo(UPDATED_SECURITY_CLEARANCE);
        assertThat(testApplicantForm.isMedicalClearance()).isEqualTo(UPDATED_MEDICAL_CLEARANCE);
        assertThat(testApplicantForm.isEmployeeWellness()).isEqualTo(UPDATED_EMPLOYEE_WELLNESS);
        assertThat(testApplicantForm.isEmergencyContact()).isEqualTo(UPDATED_EMERGENCY_CONTACT);
    }

    @Test
    @Transactional
    public void updateNonExistingApplicantForm() throws Exception {
        int databaseSizeBeforeUpdate = applicantFormRepository.findAll().size();

        // Create the ApplicantForm

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApplicantFormMockMvc.perform(put("/api/applicant-forms")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(applicantForm)))
            .andExpect(status().isBadRequest());

        // Validate the ApplicantForm in the database
        List<ApplicantForm> applicantFormList = applicantFormRepository.findAll();
        assertThat(applicantFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteApplicantForm() throws Exception {
        // Initialize the database
        applicantFormService.save(applicantForm);

        int databaseSizeBeforeDelete = applicantFormRepository.findAll().size();

        // Delete the applicantForm
        restApplicantFormMockMvc.perform(delete("/api/applicant-forms/{id}", applicantForm.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ApplicantForm> applicantFormList = applicantFormRepository.findAll();
        assertThat(applicantFormList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
