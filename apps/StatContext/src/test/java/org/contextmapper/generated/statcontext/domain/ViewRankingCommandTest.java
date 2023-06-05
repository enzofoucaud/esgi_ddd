package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewRankingCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewRankingCommand.class);
        ViewRankingCommand viewRankingCommand1 = new ViewRankingCommand();
        viewRankingCommand1.setId(1L);
        ViewRankingCommand viewRankingCommand2 = new ViewRankingCommand();
        viewRankingCommand2.setId(viewRankingCommand1.getId());
        assertThat(viewRankingCommand1).isEqualTo(viewRankingCommand2);
        viewRankingCommand2.setId(2L);
        assertThat(viewRankingCommand1).isNotEqualTo(viewRankingCommand2);
        viewRankingCommand1.setId(null);
        assertThat(viewRankingCommand1).isNotEqualTo(viewRankingCommand2);
    }
}
