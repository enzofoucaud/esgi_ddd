package org.contextmapper.generated.newquestioncontext.service.mapper;

import org.contextmapper.generated.newquestioncontext.domain.NewQuestion;
import org.contextmapper.generated.newquestioncontext.domain.NotifiedUsers;
import org.contextmapper.generated.newquestioncontext.service.dto.NewQuestionDTO;
import org.contextmapper.generated.newquestioncontext.service.dto.NotifiedUsersDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotifiedUsers} and its DTO {@link NotifiedUsersDTO}.
 */
@Mapper(componentModel = "spring")
public interface NotifiedUsersMapper extends EntityMapper<NotifiedUsersDTO, NotifiedUsers> {
    @Mapping(target = "question", source = "question", qualifiedByName = "newQuestionId")
    NotifiedUsersDTO toDto(NotifiedUsers s);

    @Named("newQuestionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    NewQuestionDTO toDtoNewQuestionId(NewQuestion newQuestion);
}
