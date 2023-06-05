package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EarnPointTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EarnPoint.class);
        EarnPoint earnPoint1 = new EarnPoint();
        earnPoint1.setId(1L);
        EarnPoint earnPoint2 = new EarnPoint();
        earnPoint2.setId(earnPoint1.getId());
        assertThat(earnPoint1).isEqualTo(earnPoint2);
        earnPoint2.setId(2L);
        assertThat(earnPoint1).isNotEqualTo(earnPoint2);
        earnPoint1.setId(null);
        assertThat(earnPoint1).isNotEqualTo(earnPoint2);
    }
}
