package org.contextmapper.generated.answercontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.answercontext.domain.NewQuestionId} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NewQuestionIdDTO implements Serializable {

    private Long id;

    private Long questionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NewQuestionIdDTO)) {
            return false;
        }

        NewQuestionIdDTO newQuestionIdDTO = (NewQuestionIdDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, newQuestionIdDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NewQuestionIdDTO{" +
            "id=" + getId() +
            ", questionId=" + getQuestionId() +
            "}";
    }
}
