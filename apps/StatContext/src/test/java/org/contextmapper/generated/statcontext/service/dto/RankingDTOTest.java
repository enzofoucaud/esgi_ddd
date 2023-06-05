package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RankingDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RankingDTO.class);
        RankingDTO rankingDTO1 = new RankingDTO();
        rankingDTO1.setId(1L);
        RankingDTO rankingDTO2 = new RankingDTO();
        assertThat(rankingDTO1).isNotEqualTo(rankingDTO2);
        rankingDTO2.setId(rankingDTO1.getId());
        assertThat(rankingDTO1).isEqualTo(rankingDTO2);
        rankingDTO2.setId(2L);
        assertThat(rankingDTO1).isNotEqualTo(rankingDTO2);
        rankingDTO1.setId(null);
        assertThat(rankingDTO1).isNotEqualTo(rankingDTO2);
    }
}
