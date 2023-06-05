package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.EarnPoint;
import org.contextmapper.generated.evaluationcontext.service.dto.EarnPointDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EarnPoint} and its DTO {@link EarnPointDTO}.
 */
@Mapper(componentModel = "spring")
public interface EarnPointMapper extends EntityMapper<EarnPointDTO, EarnPoint> {}
