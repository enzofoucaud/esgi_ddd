package org.contextmapper.generated.newquestioncontext.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import org.contextmapper.generated.newquestioncontext.domain.enumeration.QuestionNotificationStatus;

/**
 * A DTO for the {@link org.contextmapper.generated.newquestioncontext.domain.NewQuestion} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NewQuestionDTO implements Serializable {

    private Long id;

    private LocalDate sentDate;

    private LocalDate viewedDate;

    private LocalDate answeredDate;

    private QuestionNotificationStatus status;

    private ResourceIdDTO resourceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDate sentDate) {
        this.sentDate = sentDate;
    }

    public LocalDate getViewedDate() {
        return viewedDate;
    }

    public void setViewedDate(LocalDate viewedDate) {
        this.viewedDate = viewedDate;
    }

    public LocalDate getAnsweredDate() {
        return answeredDate;
    }

    public void setAnsweredDate(LocalDate answeredDate) {
        this.answeredDate = answeredDate;
    }

    public QuestionNotificationStatus getStatus() {
        return status;
    }

    public void setStatus(QuestionNotificationStatus status) {
        this.status = status;
    }

    public ResourceIdDTO getResourceId() {
        return resourceId;
    }

    public void setResourceId(ResourceIdDTO resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NewQuestionDTO)) {
            return false;
        }

        NewQuestionDTO newQuestionDTO = (NewQuestionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, newQuestionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NewQuestionDTO{" +
            "id=" + getId() +
            ", sentDate='" + getSentDate() + "'" +
            ", viewedDate='" + getViewedDate() + "'" +
            ", answeredDate='" + getAnsweredDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", resourceId=" + getResourceId() +
            "}";
    }
}
