package org.contextmapper.generated.newquestioncontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NewQuestionTagInfosViewedEvent.
 */
@Entity
@Table(name = "new_question_tag_infos_viewed_event")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NewQuestionTagInfosViewedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "newQuestionTagInfosViewedEvent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "newQuestion", "newQuestionTagInfosViewedEvent" }, allowSetters = true)
    private Set<NewQuestionTagInfos> tagInfos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public NewQuestionTagInfosViewedEvent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<NewQuestionTagInfos> getTagInfos() {
        return this.tagInfos;
    }

    public void setTagInfos(Set<NewQuestionTagInfos> newQuestionTagInfos) {
        if (this.tagInfos != null) {
            this.tagInfos.forEach(i -> i.setNewQuestionTagInfosViewedEvent(null));
        }
        if (newQuestionTagInfos != null) {
            newQuestionTagInfos.forEach(i -> i.setNewQuestionTagInfosViewedEvent(this));
        }
        this.tagInfos = newQuestionTagInfos;
    }

    public NewQuestionTagInfosViewedEvent tagInfos(Set<NewQuestionTagInfos> newQuestionTagInfos) {
        this.setTagInfos(newQuestionTagInfos);
        return this;
    }

    public NewQuestionTagInfosViewedEvent addTagInfos(NewQuestionTagInfos newQuestionTagInfos) {
        this.tagInfos.add(newQuestionTagInfos);
        newQuestionTagInfos.setNewQuestionTagInfosViewedEvent(this);
        return this;
    }

    public NewQuestionTagInfosViewedEvent removeTagInfos(NewQuestionTagInfos newQuestionTagInfos) {
        this.tagInfos.remove(newQuestionTagInfos);
        newQuestionTagInfos.setNewQuestionTagInfosViewedEvent(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NewQuestionTagInfosViewedEvent)) {
            return false;
        }
        return id != null && id.equals(((NewQuestionTagInfosViewedEvent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NewQuestionTagInfosViewedEvent{" +
            "id=" + getId() +
            "}";
    }
}
