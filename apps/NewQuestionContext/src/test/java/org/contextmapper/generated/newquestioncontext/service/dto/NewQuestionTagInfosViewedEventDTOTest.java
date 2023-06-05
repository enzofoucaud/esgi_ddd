package org.contextmapper.generated.newquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.newquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NewQuestionTagInfosViewedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NewQuestionTagInfosViewedEventDTO.class);
        NewQuestionTagInfosViewedEventDTO newQuestionTagInfosViewedEventDTO1 = new NewQuestionTagInfosViewedEventDTO();
        newQuestionTagInfosViewedEventDTO1.setId(1L);
        NewQuestionTagInfosViewedEventDTO newQuestionTagInfosViewedEventDTO2 = new NewQuestionTagInfosViewedEventDTO();
        assertThat(newQuestionTagInfosViewedEventDTO1).isNotEqualTo(newQuestionTagInfosViewedEventDTO2);
        newQuestionTagInfosViewedEventDTO2.setId(newQuestionTagInfosViewedEventDTO1.getId());
        assertThat(newQuestionTagInfosViewedEventDTO1).isEqualTo(newQuestionTagInfosViewedEventDTO2);
        newQuestionTagInfosViewedEventDTO2.setId(2L);
        assertThat(newQuestionTagInfosViewedEventDTO1).isNotEqualTo(newQuestionTagInfosViewedEventDTO2);
        newQuestionTagInfosViewedEventDTO1.setId(null);
        assertThat(newQuestionTagInfosViewedEventDTO1).isNotEqualTo(newQuestionTagInfosViewedEventDTO2);
    }
}
