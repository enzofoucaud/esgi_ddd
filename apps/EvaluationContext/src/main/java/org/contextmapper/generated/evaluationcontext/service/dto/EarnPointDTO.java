package org.contextmapper.generated.evaluationcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.Difficulty;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.UserLevel;

/**
 * A DTO for the {@link org.contextmapper.generated.evaluationcontext.domain.EarnPoint} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EarnPointDTO implements Serializable {

    private Long id;

    private Integer scoreEvolution;

    private Difficulty difficulty;

    private UserLevel userLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScoreEvolution() {
        return scoreEvolution;
    }

    public void setScoreEvolution(Integer scoreEvolution) {
        this.scoreEvolution = scoreEvolution;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EarnPointDTO)) {
            return false;
        }

        EarnPointDTO earnPointDTO = (EarnPointDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, earnPointDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EarnPointDTO{" +
            "id=" + getId() +
            ", scoreEvolution=" + getScoreEvolution() +
            ", difficulty='" + getDifficulty() + "'" +
            ", userLevel='" + getUserLevel() + "'" +
            "}";
    }
}
