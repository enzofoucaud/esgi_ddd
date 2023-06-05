package org.contextmapper.generated.evaluationcontext.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.Difficulty;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.UserLevel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EarnPoint.
 */
@Entity
@Table(name = "earn_point")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EarnPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "score_evolution")
    private Integer scoreEvolution;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty")
    private Difficulty difficulty;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_level")
    private UserLevel userLevel;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EarnPoint id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScoreEvolution() {
        return this.scoreEvolution;
    }

    public EarnPoint scoreEvolution(Integer scoreEvolution) {
        this.setScoreEvolution(scoreEvolution);
        return this;
    }

    public void setScoreEvolution(Integer scoreEvolution) {
        this.scoreEvolution = scoreEvolution;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public EarnPoint difficulty(Difficulty difficulty) {
        this.setDifficulty(difficulty);
        return this;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public UserLevel getUserLevel() {
        return this.userLevel;
    }

    public EarnPoint userLevel(UserLevel userLevel) {
        this.setUserLevel(userLevel);
        return this;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EarnPoint)) {
            return false;
        }
        return id != null && id.equals(((EarnPoint) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EarnPoint{" +
            "id=" + getId() +
            ", scoreEvolution=" + getScoreEvolution() +
            ", difficulty='" + getDifficulty() + "'" +
            ", userLevel='" + getUserLevel() + "'" +
            "}";
    }
}
