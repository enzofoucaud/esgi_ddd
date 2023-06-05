package org.contextmapper.generated.newquestioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.newquestioncontext.domain.NewQuestionTagInfos} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NewQuestionTagInfosDTO implements Serializable {

    private Long id;

    private Long tagId;

    private String tagName;

    private NewQuestionDTO newQuestion;

    private NewQuestionTagInfosViewedEventDTO newQuestionTagInfosViewedEvent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public NewQuestionDTO getNewQuestion() {
        return newQuestion;
    }

    public void setNewQuestion(NewQuestionDTO newQuestion) {
        this.newQuestion = newQuestion;
    }

    public NewQuestionTagInfosViewedEventDTO getNewQuestionTagInfosViewedEvent() {
        return newQuestionTagInfosViewedEvent;
    }

    public void setNewQuestionTagInfosViewedEvent(NewQuestionTagInfosViewedEventDTO newQuestionTagInfosViewedEvent) {
        this.newQuestionTagInfosViewedEvent = newQuestionTagInfosViewedEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NewQuestionTagInfosDTO)) {
            return false;
        }

        NewQuestionTagInfosDTO newQuestionTagInfosDTO = (NewQuestionTagInfosDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, newQuestionTagInfosDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NewQuestionTagInfosDTO{" +
            "id=" + getId() +
            ", tagId=" + getTagId() +
            ", tagName='" + getTagName() + "'" +
            ", newQuestion=" + getNewQuestion() +
            ", newQuestionTagInfosViewedEvent=" + getNewQuestionTagInfosViewedEvent() +
            "}";
    }
}
