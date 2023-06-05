package org.contextmapper.generated.newquestioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.newquestioncontext.domain.NotifiedQuestionEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NotifiedQuestionEventDTO implements Serializable {

    private Long id;

    private NotifiedUsersDTO questionResource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotifiedUsersDTO getQuestionResource() {
        return questionResource;
    }

    public void setQuestionResource(NotifiedUsersDTO questionResource) {
        this.questionResource = questionResource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotifiedQuestionEventDTO)) {
            return false;
        }

        NotifiedQuestionEventDTO notifiedQuestionEventDTO = (NotifiedQuestionEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, notifiedQuestionEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotifiedQuestionEventDTO{" +
            "id=" + getId() +
            ", questionResource=" + getQuestionResource() +
            "}";
    }
}
