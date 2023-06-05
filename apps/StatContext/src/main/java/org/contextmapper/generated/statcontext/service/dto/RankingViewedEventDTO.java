package org.contextmapper.generated.statcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.statcontext.domain.RankingViewedEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RankingViewedEventDTO implements Serializable {

    private Long id;

    private StatisticSubjectTagDTO tag;

    private RankingDTO newUserRanking;

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

    public RankingDTO getNewUserRanking() {
        return newUserRanking;
    }

    public void setNewUserRanking(RankingDTO newUserRanking) {
        this.newUserRanking = newUserRanking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RankingViewedEventDTO)) {
            return false;
        }

        RankingViewedEventDTO rankingViewedEventDTO = (RankingViewedEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, rankingViewedEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RankingViewedEventDTO{" +
            "id=" + getId() +
            ", tag=" + getTag() +
            ", newUserRanking=" + getNewUserRanking() +
            "}";
    }
}
