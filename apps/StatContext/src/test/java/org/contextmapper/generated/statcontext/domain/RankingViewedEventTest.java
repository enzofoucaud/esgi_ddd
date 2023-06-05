package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RankingViewedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RankingViewedEvent.class);
        RankingViewedEvent rankingViewedEvent1 = new RankingViewedEvent();
        rankingViewedEvent1.setId(1L);
        RankingViewedEvent rankingViewedEvent2 = new RankingViewedEvent();
        rankingViewedEvent2.setId(rankingViewedEvent1.getId());
        assertThat(rankingViewedEvent1).isEqualTo(rankingViewedEvent2);
        rankingViewedEvent2.setId(2L);
        assertThat(rankingViewedEvent1).isNotEqualTo(rankingViewedEvent2);
        rankingViewedEvent1.setId(null);
        assertThat(rankingViewedEvent1).isNotEqualTo(rankingViewedEvent2);
    }
}
