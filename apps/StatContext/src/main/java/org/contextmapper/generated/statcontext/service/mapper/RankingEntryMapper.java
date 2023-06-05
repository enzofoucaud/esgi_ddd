package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.Ranking;
import org.contextmapper.generated.statcontext.domain.RankingEntry;
import org.contextmapper.generated.statcontext.domain.StatisticSubjectUser;
import org.contextmapper.generated.statcontext.service.dto.RankingDTO;
import org.contextmapper.generated.statcontext.service.dto.RankingEntryDTO;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RankingEntry} and its DTO {@link RankingEntryDTO}.
 */
@Mapper(componentModel = "spring")
public interface RankingEntryMapper extends EntityMapper<RankingEntryDTO, RankingEntry> {
    @Mapping(target = "users", source = "users", qualifiedByName = "statisticSubjectUserId")
    @Mapping(target = "ranking", source = "ranking", qualifiedByName = "rankingId")
    RankingEntryDTO toDto(RankingEntry s);

    @Named("statisticSubjectUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectUserDTO toDtoStatisticSubjectUserId(StatisticSubjectUser statisticSubjectUser);

    @Named("rankingId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    RankingDTO toDtoRankingId(Ranking ranking);
}
