package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RankingEntryDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RankingEntryDTO.class);
        RankingEntryDTO rankingEntryDTO1 = new RankingEntryDTO();
        rankingEntryDTO1.setId(1L);
        RankingEntryDTO rankingEntryDTO2 = new RankingEntryDTO();
        assertThat(rankingEntryDTO1).isNotEqualTo(rankingEntryDTO2);
        rankingEntryDTO2.setId(rankingEntryDTO1.getId());
        assertThat(rankingEntryDTO1).isEqualTo(rankingEntryDTO2);
        rankingEntryDTO2.setId(2L);
        assertThat(rankingEntryDTO1).isNotEqualTo(rankingEntryDTO2);
        rankingEntryDTO1.setId(null);
        assertThat(rankingEntryDTO1).isNotEqualTo(rankingEntryDTO2);
    }
}
