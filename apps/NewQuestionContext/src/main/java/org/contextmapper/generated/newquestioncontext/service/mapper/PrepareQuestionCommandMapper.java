package org.contextmapper.generated.newquestioncontext.service.mapper;

import org.contextmapper.generated.newquestioncontext.domain.PrepareQuestionCommand;
import org.contextmapper.generated.newquestioncontext.service.dto.PrepareQuestionCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PrepareQuestionCommand} and its DTO {@link PrepareQuestionCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface PrepareQuestionCommandMapper extends EntityMapper<PrepareQuestionCommandDTO, PrepareQuestionCommand> {}
