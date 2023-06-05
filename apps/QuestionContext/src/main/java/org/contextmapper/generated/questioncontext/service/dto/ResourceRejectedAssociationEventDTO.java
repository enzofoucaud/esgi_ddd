package org.contextmapper.generated.questioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.questioncontext.domain.ResourceRejectedAssociationEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResourceRejectedAssociationEventDTO implements Serializable {

    private Long id;

    private QuestionResourceDTO questionId;

    private QuestionResourceTagInfosDTO tagInfos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionResourceDTO getQuestionId() {
        return questionId;
    }

    public void setQuestionId(QuestionResourceDTO questionId) {
        this.questionId = questionId;
    }

    public QuestionResourceTagInfosDTO getTagInfos() {
        return tagInfos;
    }

    public void setTagInfos(QuestionResourceTagInfosDTO tagInfos) {
        this.tagInfos = tagInfos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResourceRejectedAssociationEventDTO)) {
            return false;
        }

        ResourceRejectedAssociationEventDTO resourceRejectedAssociationEventDTO = (ResourceRejectedAssociationEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, resourceRejectedAssociationEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResourceRejectedAssociationEventDTO{" +
            "id=" + getId() +
            ", questionId=" + getQuestionId() +
            ", tagInfos=" + getTagInfos() +
            "}";
    }
}
