package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RankingViewedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RankingViewedEventDTO.class);
        RankingViewedEventDTO rankingViewedEventDTO1 = new RankingViewedEventDTO();
        rankingViewedEventDTO1.setId(1L);
        RankingViewedEventDTO rankingViewedEventDTO2 = new RankingViewedEventDTO();
        assertThat(rankingViewedEventDTO1).isNotEqualTo(rankingViewedEventDTO2);
        rankingViewedEventDTO2.setId(rankingViewedEventDTO1.getId());
        assertThat(rankingViewedEventDTO1).isEqualTo(rankingViewedEventDTO2);
        rankingViewedEventDTO2.setId(2L);
        assertThat(rankingViewedEventDTO1).isNotEqualTo(rankingViewedEventDTO2);
        rankingViewedEventDTO1.setId(null);
        assertThat(rankingViewedEventDTO1).isNotEqualTo(rankingViewedEventDTO2);
    }
}
