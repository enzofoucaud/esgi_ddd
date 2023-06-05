package org.contextmapper.generated.newquestioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.newquestioncontext.domain.NewQuestionTagInfosViewedEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NewQuestionTagInfosViewedEventDTO implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NewQuestionTagInfosViewedEventDTO)) {
            return false;
        }

        NewQuestionTagInfosViewedEventDTO newQuestionTagInfosViewedEventDTO = (NewQuestionTagInfosViewedEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, newQuestionTagInfosViewedEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NewQuestionTagInfosViewedEventDTO{" +
            "id=" + getId() +
            "}";
    }
}
