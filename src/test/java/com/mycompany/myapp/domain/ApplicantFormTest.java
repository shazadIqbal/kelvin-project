package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ApplicantFormTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApplicantForm.class);
        ApplicantForm applicantForm1 = new ApplicantForm();
        applicantForm1.setId(1L);
        ApplicantForm applicantForm2 = new ApplicantForm();
        applicantForm2.setId(applicantForm1.getId());
        assertThat(applicantForm1).isEqualTo(applicantForm2);
        applicantForm2.setId(2L);
        assertThat(applicantForm1).isNotEqualTo(applicantForm2);
        applicantForm1.setId(null);
        assertThat(applicantForm1).isNotEqualTo(applicantForm2);
    }
}
