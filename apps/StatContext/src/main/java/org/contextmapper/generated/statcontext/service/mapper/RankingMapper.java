package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.Ranking;
import org.contextmapper.generated.statcontext.service.dto.RankingDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ranking} and its DTO {@link RankingDTO}.
 */
@Mapper(componentModel = "spring")
public interface RankingMapper extends EntityMapper<RankingDTO, Ranking> {}
