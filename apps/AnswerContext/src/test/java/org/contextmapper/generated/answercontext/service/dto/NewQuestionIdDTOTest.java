package org.contextmapper.generated.answercontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NewQuestionIdDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NewQuestionIdDTO.class);
        NewQuestionIdDTO newQuestionIdDTO1 = new NewQuestionIdDTO();
        newQuestionIdDTO1.setId(1L);
        NewQuestionIdDTO newQuestionIdDTO2 = new NewQuestionIdDTO();
        assertThat(newQuestionIdDTO1).isNotEqualTo(newQuestionIdDTO2);
        newQuestionIdDTO2.setId(newQuestionIdDTO1.getId());
        assertThat(newQuestionIdDTO1).isEqualTo(newQuestionIdDTO2);
        newQuestionIdDTO2.setId(2L);
        assertThat(newQuestionIdDTO1).isNotEqualTo(newQuestionIdDTO2);
        newQuestionIdDTO1.setId(null);
        assertThat(newQuestionIdDTO1).isNotEqualTo(newQuestionIdDTO2);
    }
}
