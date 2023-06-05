package org.contextmapper.generated.newquestioncontext.service.mapper;

import org.contextmapper.generated.newquestioncontext.domain.NewQuestion;
import org.contextmapper.generated.newquestioncontext.domain.ViewTagsForQuestionCommand;
import org.contextmapper.generated.newquestioncontext.service.dto.NewQuestionDTO;
import org.contextmapper.generated.newquestioncontext.service.dto.ViewTagsForQuestionCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ViewTagsForQuestionCommand} and its DTO {@link ViewTagsForQuestionCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface ViewTagsForQuestionCommandMapper extends EntityMapper<ViewTagsForQuestionCommandDTO, ViewTagsForQuestionCommand> {
    @Mapping(target = "questionToSend", source = "questionToSend", qualifiedByName = "newQuestionId")
    ViewTagsForQuestionCommandDTO toDto(ViewTagsForQuestionCommand s);

    @Named("newQuestionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    NewQuestionDTO toDtoNewQuestionId(NewQuestion newQuestion);
}
