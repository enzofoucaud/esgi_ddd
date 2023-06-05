package org.contextmapper.generated.statcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.statcontext.domain.ViewRankingCommand} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ViewRankingCommandDTO implements Serializable {

    private Long id;

    private StatisticSubjectTagDTO tag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatisticSubjectTagDTO getTag() {
        return tag;
    }

    public void setTag(StatisticSubjectTagDTO tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewRankingCommandDTO)) {
            return false;
        }

        ViewRankingCommandDTO viewRankingCommandDTO = (ViewRankingCommandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, viewRankingCommandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ViewRankingCommandDTO{" +
            "id=" + getId() +
            ", tag=" + getTag() +
            "}";
    }
}
