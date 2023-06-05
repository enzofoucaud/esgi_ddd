package org.contextmapper.generated.statcontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RankingViewedEvent.
 */
@Entity
@Table(name = "ranking_viewed_event")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RankingViewedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private StatisticSubjectTag tag;

    @JsonIgnoreProperties(value = { "entries" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Ranking newUserRanking;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public RankingViewedEvent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatisticSubjectTag getTag() {
        return this.tag;
    }

    public void setTag(StatisticSubjectTag statisticSubjectTag) {
        this.tag = statisticSubjectTag;
    }

    public RankingViewedEvent tag(StatisticSubjectTag statisticSubjectTag) {
        this.setTag(statisticSubjectTag);
        return this;
    }

    public Ranking getNewUserRanking() {
        return this.newUserRanking;
    }

    public void setNewUserRanking(Ranking ranking) {
        this.newUserRanking = ranking;
    }

    public RankingViewedEvent newUserRanking(Ranking ranking) {
        this.setNewUserRanking(ranking);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RankingViewedEvent)) {
            return false;
        }
        return id != null && id.equals(((RankingViewedEvent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RankingViewedEvent{" +
            "id=" + getId() +
            "}";
    }
}
