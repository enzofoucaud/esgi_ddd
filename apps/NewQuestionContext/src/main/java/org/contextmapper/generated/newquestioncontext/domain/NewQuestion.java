package org.contextmapper.generated.newquestioncontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.contextmapper.generated.newquestioncontext.domain.enumeration.QuestionNotificationStatus;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NewQuestion.
 */
@Entity
@Table(name = "new_question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NewQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "sent_date")
    private LocalDate sentDate;

    @Column(name = "viewed_date")
    private LocalDate viewedDate;

    @Column(name = "answered_date")
    private LocalDate answeredDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private QuestionNotificationStatus status;

    @OneToOne
    @JoinColumn(unique = true)
    private ResourceId resourceId;

    @OneToMany(mappedBy = "newQuestion")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "newQuestion", "newQuestionTagInfosViewedEvent" }, allowSetters = true)
    private Set<NewQuestionTagInfos> tags = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public NewQuestion id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getSentDate() {
        return this.sentDate;
    }

    public NewQuestion sentDate(LocalDate sentDate) {
        this.setSentDate(sentDate);
        return this;
    }

    public void setSentDate(LocalDate sentDate) {
        this.sentDate = sentDate;
    }

    public LocalDate getViewedDate() {
        return this.viewedDate;
    }

    public NewQuestion viewedDate(LocalDate viewedDate) {
        this.setViewedDate(viewedDate);
        return this;
    }

    public void setViewedDate(LocalDate viewedDate) {
        this.viewedDate = viewedDate;
    }

    public LocalDate getAnsweredDate() {
        return this.answeredDate;
    }

    public NewQuestion answeredDate(LocalDate answeredDate) {
        this.setAnsweredDate(answeredDate);
        return this;
    }

    public void setAnsweredDate(LocalDate answeredDate) {
        this.answeredDate = answeredDate;
    }

    public QuestionNotificationStatus getStatus() {
        return this.status;
    }

    public NewQuestion status(QuestionNotificationStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(QuestionNotificationStatus status) {
        this.status = status;
    }

    public ResourceId getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(ResourceId resourceId) {
        this.resourceId = resourceId;
    }

    public NewQuestion resourceId(ResourceId resourceId) {
        this.setResourceId(resourceId);
        return this;
    }

    public Set<NewQuestionTagInfos> getTags() {
        return this.tags;
    }

    public void setTags(Set<NewQuestionTagInfos> newQuestionTagInfos) {
        if (this.tags != null) {
            this.tags.forEach(i -> i.setNewQuestion(null));
        }
        if (newQuestionTagInfos != null) {
            newQuestionTagInfos.forEach(i -> i.setNewQuestion(this));
        }
        this.tags = newQuestionTagInfos;
    }

    public NewQuestion tags(Set<NewQuestionTagInfos> newQuestionTagInfos) {
        this.setTags(newQuestionTagInfos);
        return this;
    }

    public NewQuestion addTags(NewQuestionTagInfos newQuestionTagInfos) {
        this.tags.add(newQuestionTagInfos);
        newQuestionTagInfos.setNewQuestion(this);
        return this;
    }

    public NewQuestion removeTags(NewQuestionTagInfos newQuestionTagInfos) {
        this.tags.remove(newQuestionTagInfos);
        newQuestionTagInfos.setNewQuestion(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NewQuestion)) {
            return false;
        }
        return id != null && id.equals(((NewQuestion) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NewQuestion{" +
            "id=" + getId() +
            ", sentDate='" + getSentDate() + "'" +
            ", viewedDate='" + getViewedDate() + "'" +
            ", answeredDate='" + getAnsweredDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
