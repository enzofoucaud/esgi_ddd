package org.contextmapper.generated.evaluationcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.Difficulty;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.Status;

/**
 * A DTO for the {@link org.contextmapper.generated.evaluationcontext.domain.Evaluation} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EvaluationDTO implements Serializable {

    private Long id;

    private Integer score;

    private String evaluatorMail;

    private Status status;

    private Difficulty answeredQuestionDifficulty;

    private EvaluationTagDTO tag;

    private EvaluationQuestionDTO question;

    private AnsweringUserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getEvaluatorMail() {
        return evaluatorMail;
    }

    public void setEvaluatorMail(String evaluatorMail) {
        this.evaluatorMail = evaluatorMail;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Difficulty getAnsweredQuestionDifficulty() {
        return answeredQuestionDifficulty;
    }

    public void setAnsweredQuestionDifficulty(Difficulty answeredQuestionDifficulty) {
        this.answeredQuestionDifficulty = answeredQuestionDifficulty;
    }

    public EvaluationTagDTO getTag() {
        return tag;
    }

    public void setTag(EvaluationTagDTO tag) {
        this.tag = tag;
    }

    public EvaluationQuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(EvaluationQuestionDTO question) {
        this.question = question;
    }

    public AnsweringUserDTO getUser() {
        return user;
    }

    public void setUser(AnsweringUserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EvaluationDTO)) {
            return false;
        }

        EvaluationDTO evaluationDTO = (EvaluationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, evaluationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EvaluationDTO{" +
            "id=" + getId() +
            ", score=" + getScore() +
            ", evaluatorMail='" + getEvaluatorMail() + "'" +
            ", status='" + getStatus() + "'" +
            ", answeredQuestionDifficulty='" + getAnsweredQuestionDifficulty() + "'" +
            ", tag=" + getTag() +
            ", question=" + getQuestion() +
            ", user=" + getUser() +
            "}";
    }
}
