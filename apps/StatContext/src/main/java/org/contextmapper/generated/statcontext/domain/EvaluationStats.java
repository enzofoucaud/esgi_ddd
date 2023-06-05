package org.contextmapper.generated.statcontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EvaluationStats.
 */
@Entity
@Table(name = "evaluation_stats")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EvaluationStats implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "total")
    private Integer total;

    @OneToMany(mappedBy = "evaluationStats")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "user", "question", "evaluationStats" }, allowSetters = true)
    private Set<EvaluationStatEntry> evaluations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EvaluationStats id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotal() {
        return this.total;
    }

    public EvaluationStats total(Integer total) {
        this.setTotal(total);
        return this;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Set<EvaluationStatEntry> getEvaluations() {
        return this.evaluations;
    }

    public void setEvaluations(Set<EvaluationStatEntry> evaluationStatEntries) {
        if (this.evaluations != null) {
            this.evaluations.forEach(i -> i.setEvaluationStats(null));
        }
        if (evaluationStatEntries != null) {
            evaluationStatEntries.forEach(i -> i.setEvaluationStats(this));
        }
        this.evaluations = evaluationStatEntries;
    }

    public EvaluationStats evaluations(Set<EvaluationStatEntry> evaluationStatEntries) {
        this.setEvaluations(evaluationStatEntries);
        return this;
    }

    public EvaluationStats addEvaluation(EvaluationStatEntry evaluationStatEntry) {
        this.evaluations.add(evaluationStatEntry);
        evaluationStatEntry.setEvaluationStats(this);
        return this;
    }

    public EvaluationStats removeEvaluation(EvaluationStatEntry evaluationStatEntry) {
        this.evaluations.remove(evaluationStatEntry);
        evaluationStatEntry.setEvaluationStats(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EvaluationStats)) {
            return false;
        }
        return id != null && id.equals(((EvaluationStats) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EvaluationStats{" +
            "id=" + getId() +
            ", total=" + getTotal() +
            "}";
    }
}
