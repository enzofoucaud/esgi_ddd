package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.NewQuestionId;
import org.contextmapper.generated.answercontext.domain.TagChoicesListCommand;
import org.contextmapper.generated.answercontext.service.dto.NewQuestionIdDTO;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TagChoicesListCommand} and its DTO {@link TagChoicesListCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface TagChoicesListCommandMapper extends EntityMapper<TagChoicesListCommandDTO, TagChoicesListCommand> {
    @Mapping(target = "questionSent", source = "questionSent", qualifiedByName = "newQuestionIdId")
    TagChoicesListCommandDTO toDto(TagChoicesListCommand s);

    @Named("newQuestionIdId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    NewQuestionIdDTO toDtoNewQuestionIdId(NewQuestionId newQuestionId);
}
