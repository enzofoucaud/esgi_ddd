package org.contextmapper.generated.newquestioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.newquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NewQuestionTagInfosTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NewQuestionTagInfos.class);
        NewQuestionTagInfos newQuestionTagInfos1 = new NewQuestionTagInfos();
        newQuestionTagInfos1.setId(1L);
        NewQuestionTagInfos newQuestionTagInfos2 = new NewQuestionTagInfos();
        newQuestionTagInfos2.setId(newQuestionTagInfos1.getId());
        assertThat(newQuestionTagInfos1).isEqualTo(newQuestionTagInfos2);
        newQuestionTagInfos2.setId(2L);
        assertThat(newQuestionTagInfos1).isNotEqualTo(newQuestionTagInfos2);
        newQuestionTagInfos1.setId(null);
        assertThat(newQuestionTagInfos1).isNotEqualTo(newQuestionTagInfos2);
    }
}
