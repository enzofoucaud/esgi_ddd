package org.contextmapper.generated.newquestioncontext.service.mapper;

import org.contextmapper.generated.newquestioncontext.domain.NewQuestion;
import org.contextmapper.generated.newquestioncontext.domain.NewQuestionTagInfos;
import org.contextmapper.generated.newquestioncontext.domain.NewQuestionTagInfosViewedEvent;
import org.contextmapper.generated.newquestioncontext.service.dto.NewQuestionDTO;
import org.contextmapper.generated.newquestioncontext.service.dto.NewQuestionTagInfosDTO;
import org.contextmapper.generated.newquestioncontext.service.dto.NewQuestionTagInfosViewedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NewQuestionTagInfos} and its DTO {@link NewQuestionTagInfosDTO}.
 */
@Mapper(componentModel = "spring")
public interface NewQuestionTagInfosMapper extends EntityMapper<NewQuestionTagInfosDTO, NewQuestionTagInfos> {
    @Mapping(target = "newQuestion", source = "newQuestion", qualifiedByName = "newQuestionId")
    @Mapping(
        target = "newQuestionTagInfosViewedEvent",
        source = "newQuestionTagInfosViewedEvent",
        qualifiedByName = "newQuestionTagInfosViewedEventId"
    )
    NewQuestionTagInfosDTO toDto(NewQuestionTagInfos s);

    @Named("newQuestionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    NewQuestionDTO toDtoNewQuestionId(NewQuestion newQuestion);

    @Named("newQuestionTagInfosViewedEventId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    NewQuestionTagInfosViewedEventDTO toDtoNewQuestionTagInfosViewedEventId(NewQuestionTagInfosViewedEvent newQuestionTagInfosViewedEvent);
}
