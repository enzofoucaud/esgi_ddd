package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RankingEntryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RankingEntry.class);
        RankingEntry rankingEntry1 = new RankingEntry();
        rankingEntry1.setId(1L);
        RankingEntry rankingEntry2 = new RankingEntry();
        rankingEntry2.setId(rankingEntry1.getId());
        assertThat(rankingEntry1).isEqualTo(rankingEntry2);
        rankingEntry2.setId(2L);
        assertThat(rankingEntry1).isNotEqualTo(rankingEntry2);
        rankingEntry1.setId(null);
        assertThat(rankingEntry1).isNotEqualTo(rankingEntry2);
    }
}
