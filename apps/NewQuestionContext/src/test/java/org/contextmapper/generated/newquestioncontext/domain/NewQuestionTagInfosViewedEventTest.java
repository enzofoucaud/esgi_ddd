package org.contextmapper.generated.newquestioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.newquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NewQuestionTagInfosViewedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NewQuestionTagInfosViewedEvent.class);
        NewQuestionTagInfosViewedEvent newQuestionTagInfosViewedEvent1 = new NewQuestionTagInfosViewedEvent();
        newQuestionTagInfosViewedEvent1.setId(1L);
        NewQuestionTagInfosViewedEvent newQuestionTagInfosViewedEvent2 = new NewQuestionTagInfosViewedEvent();
        newQuestionTagInfosViewedEvent2.setId(newQuestionTagInfosViewedEvent1.getId());
        assertThat(newQuestionTagInfosViewedEvent1).isEqualTo(newQuestionTagInfosViewedEvent2);
        newQuestionTagInfosViewedEvent2.setId(2L);
        assertThat(newQuestionTagInfosViewedEvent1).isNotEqualTo(newQuestionTagInfosViewedEvent2);
        newQuestionTagInfosViewedEvent1.setId(null);
        assertThat(newQuestionTagInfosViewedEvent1).isNotEqualTo(newQuestionTagInfosViewedEvent2);
    }
}
