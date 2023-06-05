package org.contextmapper.generated.evaluationcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.Difficulty;

/**
 * A DTO for the {@link org.contextmapper.generated.evaluationcontext.domain.CreateEvaluationCommand} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreateEvaluationCommandDTO implements Serializable {

    private Long id;

    private Difficulty difficulty;

    private EvaluatedAnswerDTO answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public EvaluatedAnswerDTO getAnswer() {
        return answer;
    }

    public void setAnswer(EvaluatedAnswerDTO answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateEvaluationCommandDTO)) {
            return false;
        }

        CreateEvaluationCommandDTO createEvaluationCommandDTO = (CreateEvaluationCommandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, createEvaluationCommandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreateEvaluationCommandDTO{" +
            "id=" + getId() +
            ", difficulty='" + getDifficulty() + "'" +
            ", answer=" + getAnswer() +
            "}";
    }
}
