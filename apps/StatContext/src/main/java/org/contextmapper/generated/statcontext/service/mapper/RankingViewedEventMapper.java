package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.Ranking;
import org.contextmapper.generated.statcontext.domain.RankingViewedEvent;
import org.contextmapper.generated.statcontext.domain.StatisticSubjectTag;
import org.contextmapper.generated.statcontext.service.dto.RankingDTO;
import org.contextmapper.generated.statcontext.service.dto.RankingViewedEventDTO;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectTagDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RankingViewedEvent} and its DTO {@link RankingViewedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface RankingViewedEventMapper extends EntityMapper<RankingViewedEventDTO, RankingViewedEvent> {
    @Mapping(target = "tag", source = "tag", qualifiedByName = "statisticSubjectTagId")
    @Mapping(target = "newUserRanking", source = "newUserRanking", qualifiedByName = "rankingId")
    RankingViewedEventDTO toDto(RankingViewedEvent s);

    @Named("statisticSubjectTagId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectTagDTO toDtoStatisticSubjectTagId(StatisticSubjectTag statisticSubjectTag);

    @Named("rankingId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    RankingDTO toDtoRankingId(Ranking ranking);
}
