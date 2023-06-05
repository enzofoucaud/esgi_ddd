package org.contextmapper.generated.questioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceRejectedAssociationEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceRejectedAssociationEventDTO.class);
        ResourceRejectedAssociationEventDTO resourceRejectedAssociationEventDTO1 = new ResourceRejectedAssociationEventDTO();
        resourceRejectedAssociationEventDTO1.setId(1L);
        ResourceRejectedAssociationEventDTO resourceRejectedAssociationEventDTO2 = new ResourceRejectedAssociationEventDTO();
        assertThat(resourceRejectedAssociationEventDTO1).isNotEqualTo(resourceRejectedAssociationEventDTO2);
        resourceRejectedAssociationEventDTO2.setId(resourceRejectedAssociationEventDTO1.getId());
        assertThat(resourceRejectedAssociationEventDTO1).isEqualTo(resourceRejectedAssociationEventDTO2);
        resourceRejectedAssociationEventDTO2.setId(2L);
        assertThat(resourceRejectedAssociationEventDTO1).isNotEqualTo(resourceRejectedAssociationEventDTO2);
        resourceRejectedAssociationEventDTO1.setId(null);
        assertThat(resourceRejectedAssociationEventDTO1).isNotEqualTo(resourceRejectedAssociationEventDTO2);
    }
}
