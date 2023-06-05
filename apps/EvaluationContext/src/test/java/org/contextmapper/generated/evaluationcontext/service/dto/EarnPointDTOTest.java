package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EarnPointDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EarnPointDTO.class);
        EarnPointDTO earnPointDTO1 = new EarnPointDTO();
        earnPointDTO1.setId(1L);
        EarnPointDTO earnPointDTO2 = new EarnPointDTO();
        assertThat(earnPointDTO1).isNotEqualTo(earnPointDTO2);
        earnPointDTO2.setId(earnPointDTO1.getId());
        assertThat(earnPointDTO1).isEqualTo(earnPointDTO2);
        earnPointDTO2.setId(2L);
        assertThat(earnPointDTO1).isNotEqualTo(earnPointDTO2);
        earnPointDTO1.setId(null);
        assertThat(earnPointDTO1).isNotEqualTo(earnPointDTO2);
    }
}
