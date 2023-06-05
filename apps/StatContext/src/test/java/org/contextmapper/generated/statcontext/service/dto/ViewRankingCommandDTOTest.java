package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewRankingCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewRankingCommandDTO.class);
        ViewRankingCommandDTO viewRankingCommandDTO1 = new ViewRankingCommandDTO();
        viewRankingCommandDTO1.setId(1L);
        ViewRankingCommandDTO viewRankingCommandDTO2 = new ViewRankingCommandDTO();
        assertThat(viewRankingCommandDTO1).isNotEqualTo(viewRankingCommandDTO2);
        viewRankingCommandDTO2.setId(viewRankingCommandDTO1.getId());
        assertThat(viewRankingCommandDTO1).isEqualTo(viewRankingCommandDTO2);
        viewRankingCommandDTO2.setId(2L);
        assertThat(viewRankingCommandDTO1).isNotEqualTo(viewRankingCommandDTO2);
        viewRankingCommandDTO1.setId(null);
        assertThat(viewRankingCommandDTO1).isNotEqualTo(viewRankingCommandDTO2);
    }
}
