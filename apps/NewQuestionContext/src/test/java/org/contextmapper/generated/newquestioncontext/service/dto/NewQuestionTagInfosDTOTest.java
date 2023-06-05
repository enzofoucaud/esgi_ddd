package org.contextmapper.generated.newquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.newquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NewQuestionTagInfosDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NewQuestionTagInfosDTO.class);
        NewQuestionTagInfosDTO newQuestionTagInfosDTO1 = new NewQuestionTagInfosDTO();
        newQuestionTagInfosDTO1.setId(1L);
        NewQuestionTagInfosDTO newQuestionTagInfosDTO2 = new NewQuestionTagInfosDTO();
        assertThat(newQuestionTagInfosDTO1).isNotEqualTo(newQuestionTagInfosDTO2);
        newQuestionTagInfosDTO2.setId(newQuestionTagInfosDTO1.getId());
        assertThat(newQuestionTagInfosDTO1).isEqualTo(newQuestionTagInfosDTO2);
        newQuestionTagInfosDTO2.setId(2L);
        assertThat(newQuestionTagInfosDTO1).isNotEqualTo(newQuestionTagInfosDTO2);
        newQuestionTagInfosDTO1.setId(null);
        assertThat(newQuestionTagInfosDTO1).isNotEqualTo(newQuestionTagInfosDTO2);
    }
}
