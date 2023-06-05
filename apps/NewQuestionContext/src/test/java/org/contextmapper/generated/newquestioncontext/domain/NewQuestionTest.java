package org.contextmapper.generated.newquestioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.newquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NewQuestionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NewQuestion.class);
        NewQuestion newQuestion1 = new NewQuestion();
        newQuestion1.setId(1L);
        NewQuestion newQuestion2 = new NewQuestion();
        newQuestion2.setId(newQuestion1.getId());
        assertThat(newQuestion1).isEqualTo(newQuestion2);
        newQuestion2.setId(2L);
        assertThat(newQuestion1).isNotEqualTo(newQuestion2);
        newQuestion1.setId(null);
        assertThat(newQuestion1).isNotEqualTo(newQuestion2);
    }
}
