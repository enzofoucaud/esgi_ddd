package org.contextmapper.generated.newquestioncontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NewQuestionTagInfos.
 */
@Entity
@Table(name = "new_question_tag_infos")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NewQuestionTagInfos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "tag_name")
    private String tagName;

    @ManyToOne
    @JsonIgnoreProperties(value = { "resourceId", "tags" }, allowSetters = true)
    private NewQuestion newQuestion;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tagInfos" }, allowSetters = true)
    private NewQuestionTagInfosViewedEvent newQuestionTagInfosViewedEvent;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public NewQuestionTagInfos id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTagId() {
        return this.tagId;
    }

    public NewQuestionTagInfos tagId(Long tagId) {
        this.setTagId(tagId);
        return this;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return this.tagName;
    }

    public NewQuestionTagInfos tagName(String tagName) {
        this.setTagName(tagName);
        return this;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public NewQuestion getNewQuestion() {
        return this.newQuestion;
    }

    public void setNewQuestion(NewQuestion newQuestion) {
        this.newQuestion = newQuestion;
    }

    public NewQuestionTagInfos newQuestion(NewQuestion newQuestion) {
        this.setNewQuestion(newQuestion);
        return this;
    }

    public NewQuestionTagInfosViewedEvent getNewQuestionTagInfosViewedEvent() {
        return this.newQuestionTagInfosViewedEvent;
    }

    public void setNewQuestionTagInfosViewedEvent(NewQuestionTagInfosViewedEvent newQuestionTagInfosViewedEvent) {
        this.newQuestionTagInfosViewedEvent = newQuestionTagInfosViewedEvent;
    }

    public NewQuestionTagInfos newQuestionTagInfosViewedEvent(NewQuestionTagInfosViewedEvent newQuestionTagInfosViewedEvent) {
        this.setNewQuestionTagInfosViewedEvent(newQuestionTagInfosViewedEvent);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NewQuestionTagInfos)) {
            return false;
        }
        return id != null && id.equals(((NewQuestionTagInfos) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NewQuestionTagInfos{" +
            "id=" + getId() +
            ", tagId=" + getTagId() +
            ", tagName='" + getTagName() + "'" +
            "}";
    }
}
