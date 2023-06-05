package org.contextmapper.generated.newquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.newquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NewQuestionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NewQuestionDTO.class);
        NewQuestionDTO newQuestionDTO1 = new NewQuestionDTO();
        newQuestionDTO1.setId(1L);
        NewQuestionDTO newQuestionDTO2 = new NewQuestionDTO();
        assertThat(newQuestionDTO1).isNotEqualTo(newQuestionDTO2);
        newQuestionDTO2.setId(newQuestionDTO1.getId());
        assertThat(newQuestionDTO1).isEqualTo(newQuestionDTO2);
        newQuestionDTO2.setId(2L);
        assertThat(newQuestionDTO1).isNotEqualTo(newQuestionDTO2);
        newQuestionDTO1.setId(null);
        assertThat(newQuestionDTO1).isNotEqualTo(newQuestionDTO2);
    }
}
