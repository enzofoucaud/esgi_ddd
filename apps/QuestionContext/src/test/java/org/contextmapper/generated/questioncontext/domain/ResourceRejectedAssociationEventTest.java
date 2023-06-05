package org.contextmapper.generated.questioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceRejectedAssociationEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceRejectedAssociationEvent.class);
        ResourceRejectedAssociationEvent resourceRejectedAssociationEvent1 = new ResourceRejectedAssociationEvent();
        resourceRejectedAssociationEvent1.setId(1L);
        ResourceRejectedAssociationEvent resourceRejectedAssociationEvent2 = new ResourceRejectedAssociationEvent();
        resourceRejectedAssociationEvent2.setId(resourceRejectedAssociationEvent1.getId());
        assertThat(resourceRejectedAssociationEvent1).isEqualTo(resourceRejectedAssociationEvent2);
        resourceRejectedAssociationEvent2.setId(2L);
        assertThat(resourceRejectedAssociationEvent1).isNotEqualTo(resourceRejectedAssociationEvent2);
        resourceRejectedAssociationEvent1.setId(null);
        assertThat(resourceRejectedAssociationEvent1).isNotEqualTo(resourceRejectedAssociationEvent2);
    }
}
