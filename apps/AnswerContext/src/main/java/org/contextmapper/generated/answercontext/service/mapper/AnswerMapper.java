package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.Answer;
import org.contextmapper.generated.answercontext.domain.AnsweredTag;
import org.contextmapper.generated.answercontext.domain.NewQuestionId;
import org.contextmapper.generated.answercontext.domain.UserEmail;
import org.contextmapper.generated.answercontext.service.dto.AnswerDTO;
import org.contextmapper.generated.answercontext.service.dto.AnsweredTagDTO;
import org.contextmapper.generated.answercontext.service.dto.NewQuestionIdDTO;
import org.contextmapper.generated.answercontext.service.dto.UserEmailDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Answer} and its DTO {@link AnswerDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnswerMapper extends EntityMapper<AnswerDTO, Answer> {
    @Mapping(target = "question", source = "question", qualifiedByName = "newQuestionIdId")
    @Mapping(target = "answeredTag", source = "answeredTag", qualifiedByName = "answeredTagId")
    @Mapping(target = "userEmail", source = "userEmail", qualifiedByName = "userEmailId")
    AnswerDTO toDto(Answer s);

    @Named("newQuestionIdId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    NewQuestionIdDTO toDtoNewQuestionIdId(NewQuestionId newQuestionId);

    @Named("answeredTagId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AnsweredTagDTO toDtoAnsweredTagId(AnsweredTag answeredTag);

    @Named("userEmailId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserEmailDTO toDtoUserEmailId(UserEmail userEmail);
}
