package com.mycompany.myapp.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A ApplicantForm.
 */
@Entity
@Table(name = "applicant_form")
public class ApplicantForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "user_image")
    private byte[] userImage;

    @Column(name = "user_image_content_type")
    private String userImageContentType;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "recevier_email")
    private String recevierEmail;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private String gender;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @Column(name = "date_of_birth")
    private Instant dateOfBirth;

    @Column(name = "visa_details")
    private String visaDetails;

    @Column(name = "citizenship")
    private String citizenship;

    @Lob
    @Column(name = "resume")
    private byte[] resume;

    @Column(name = "resume_content_type")
    private String resumeContentType;

    @Column(name = "employee_application")
    private Boolean employeeApplication;

    @Column(name = "employee_orientation")
    private Boolean employeeOrientation;

    @Column(name = "employee_identification")
    private Boolean employeeIdentification;

    @Column(name = "security_clearance")
    private Boolean securityClearance;

    @Column(name = "medical_clearance")
    private Boolean medicalClearance;

    @Column(name = "employee_wellness")
    private Boolean employeeWellness;

    @Column(name = "emergency_contact")
    private Boolean emergencyContact;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getUserImage() {
        return userImage;
    }

    public ApplicantForm userImage(byte[] userImage) {
        this.userImage = userImage;
        return this;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public String getUserImageContentType() {
        return userImageContentType;
    }

    public ApplicantForm userImageContentType(String userImageContentType) {
        this.userImageContentType = userImageContentType;
        return this;
    }

    public void setUserImageContentType(String userImageContentType) {
        this.userImageContentType = userImageContentType;
    }

    public String getName() {
        return name;
    }

    public ApplicantForm name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public ApplicantForm email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRecevierEmail() {
        return recevierEmail;
    }

    public ApplicantForm recevierEmail(String recevierEmail) {
        this.recevierEmail = recevierEmail;
        return this;
    }

    public void setRecevierEmail(String recevierEmail) {
        this.recevierEmail = recevierEmail;
    }

    public String getPhone() {
        return phone;
    }

    public ApplicantForm phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public ApplicantForm address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public ApplicantForm gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public ApplicantForm placeOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
        return this;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Instant getDateOfBirth() {
        return dateOfBirth;
    }

    public ApplicantForm dateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getVisaDetails() {
        return visaDetails;
    }

    public ApplicantForm visaDetails(String visaDetails) {
        this.visaDetails = visaDetails;
        return this;
    }

    public void setVisaDetails(String visaDetails) {
        this.visaDetails = visaDetails;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public ApplicantForm citizenship(String citizenship) {
        this.citizenship = citizenship;
        return this;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public byte[] getResume() {
        return resume;
    }

    public ApplicantForm resume(byte[] resume) {
        this.resume = resume;
        return this;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public String getResumeContentType() {
        return resumeContentType;
    }

    public ApplicantForm resumeContentType(String resumeContentType) {
        this.resumeContentType = resumeContentType;
        return this;
    }

    public void setResumeContentType(String resumeContentType) {
        this.resumeContentType = resumeContentType;
    }

    public Boolean isEmployeeApplication() {
        return employeeApplication;
    }

    public ApplicantForm employeeApplication(Boolean employeeApplication) {
        this.employeeApplication = employeeApplication;
        return this;
    }

    public void setEmployeeApplication(Boolean employeeApplication) {
        this.employeeApplication = employeeApplication;
    }

    public Boolean isEmployeeOrientation() {
        return employeeOrientation;
    }

    public ApplicantForm employeeOrientation(Boolean employeeOrientation) {
        this.employeeOrientation = employeeOrientation;
        return this;
    }

    public void setEmployeeOrientation(Boolean employeeOrientation) {
        this.employeeOrientation = employeeOrientation;
    }

    public Boolean isEmployeeIdentification() {
        return employeeIdentification;
    }

    public ApplicantForm employeeIdentification(Boolean employeeIdentification) {
        this.employeeIdentification = employeeIdentification;
        return this;
    }

    public void setEmployeeIdentification(Boolean employeeIdentification) {
        this.employeeIdentification = employeeIdentification;
    }

    public Boolean isSecurityClearance() {
        return securityClearance;
    }

    public ApplicantForm securityClearance(Boolean securityClearance) {
        this.securityClearance = securityClearance;
        return this;
    }

    public void setSecurityClearance(Boolean securityClearance) {
        this.securityClearance = securityClearance;
    }

    public Boolean isMedicalClearance() {
        return medicalClearance;
    }

    public ApplicantForm medicalClearance(Boolean medicalClearance) {
        this.medicalClearance = medicalClearance;
        return this;
    }

    public void setMedicalClearance(Boolean medicalClearance) {
        this.medicalClearance = medicalClearance;
    }

    public Boolean isEmployeeWellness() {
        return employeeWellness;
    }

    public ApplicantForm employeeWellness(Boolean employeeWellness) {
        this.employeeWellness = employeeWellness;
        return this;
    }

    public void setEmployeeWellness(Boolean employeeWellness) {
        this.employeeWellness = employeeWellness;
    }

    public Boolean isEmergencyContact() {
        return emergencyContact;
    }

    public ApplicantForm emergencyContact(Boolean emergencyContact) {
        this.emergencyContact = emergencyContact;
        return this;
    }

    public void setEmergencyContact(Boolean emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicantForm)) {
            return false;
        }
        return id != null && id.equals(((ApplicantForm) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ApplicantForm{" +
            "id=" + getId() +
            ", userImage='" + getUserImage() + "'" +
            ", userImageContentType='" + getUserImageContentType() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", recevierEmail='" + getRecevierEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            ", gender='" + getGender() + "'" +
            ", placeOfBirth='" + getPlaceOfBirth() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", visaDetails='" + getVisaDetails() + "'" +
            ", citizenship='" + getCitizenship() + "'" +
            ", resume='" + getResume() + "'" +
            ", resumeContentType='" + getResumeContentType() + "'" +
            ", employeeApplication='" + isEmployeeApplication() + "'" +
            ", employeeOrientation='" + isEmployeeOrientation() + "'" +
            ", employeeIdentification='" + isEmployeeIdentification() + "'" +
            ", securityClearance='" + isSecurityClearance() + "'" +
            ", medicalClearance='" + isMedicalClearance() + "'" +
            ", employeeWellness='" + isEmployeeWellness() + "'" +
            ", emergencyContact='" + isEmergencyContact() + "'" +
            "}";
    }
}
