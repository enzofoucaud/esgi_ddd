package org.contextmapper.generated.newquestioncontext.service.mapper;

import org.contextmapper.generated.newquestioncontext.domain.CreatedQuestionEvent;
import org.contextmapper.generated.newquestioncontext.domain.NewQuestion;
import org.contextmapper.generated.newquestioncontext.service.dto.CreatedQuestionEventDTO;
import org.contextmapper.generated.newquestioncontext.service.dto.NewQuestionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CreatedQuestionEvent} and its DTO {@link CreatedQuestionEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreatedQuestionEventMapper extends EntityMapper<CreatedQuestionEventDTO, CreatedQuestionEvent> {
    @Mapping(target = "questionAndTag", source = "questionAndTag", qualifiedByName = "newQuestionId")
    CreatedQuestionEventDTO toDto(CreatedQuestionEvent s);

    @Named("newQuestionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    NewQuestionDTO toDtoNewQuestionId(NewQuestion newQuestion);
}
