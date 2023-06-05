package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.StatisticSubjectTag;
import org.contextmapper.generated.statcontext.domain.ViewRankingCommand;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectTagDTO;
import org.contextmapper.generated.statcontext.service.dto.ViewRankingCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ViewRankingCommand} and its DTO {@link ViewRankingCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface ViewRankingCommandMapper extends EntityMapper<ViewRankingCommandDTO, ViewRankingCommand> {
    @Mapping(target = "tag", source = "tag", qualifiedByName = "statisticSubjectTagId")
    ViewRankingCommandDTO toDto(ViewRankingCommand s);

    @Named("statisticSubjectTagId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectTagDTO toDtoStatisticSubjectTagId(StatisticSubjectTag statisticSubjectTag);
}
