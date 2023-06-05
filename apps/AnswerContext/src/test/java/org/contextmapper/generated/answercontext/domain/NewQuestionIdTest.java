package org.contextmapper.generated.answercontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NewQuestionIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NewQuestionId.class);
        NewQuestionId newQuestionId1 = new NewQuestionId();
        newQuestionId1.setId(1L);
        NewQuestionId newQuestionId2 = new NewQuestionId();
        newQuestionId2.setId(newQuestionId1.getId());
        assertThat(newQuestionId1).isEqualTo(newQuestionId2);
        newQuestionId2.setId(2L);
        assertThat(newQuestionId1).isNotEqualTo(newQuestionId2);
        newQuestionId1.setId(null);
        assertThat(newQuestionId1).isNotEqualTo(newQuestionId2);
    }
}
