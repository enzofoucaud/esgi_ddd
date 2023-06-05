package org.contextmapper.generated.newquestioncontext.service.mapper;

import org.contextmapper.generated.newquestioncontext.domain.NewQuestion;
import org.contextmapper.generated.newquestioncontext.domain.SendByPreferencesCommand;
import org.contextmapper.generated.newquestioncontext.service.dto.NewQuestionDTO;
import org.contextmapper.generated.newquestioncontext.service.dto.SendByPreferencesCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SendByPreferencesCommand} and its DTO {@link SendByPreferencesCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface SendByPreferencesCommandMapper extends EntityMapper<SendByPreferencesCommandDTO, SendByPreferencesCommand> {
    @Mapping(target = "questionToSend", source = "questionToSend", qualifiedByName = "newQuestionId")
    SendByPreferencesCommandDTO toDto(SendByPreferencesCommand s);

    @Named("newQuestionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    NewQuestionDTO toDtoNewQuestionId(NewQuestion newQuestion);
}
