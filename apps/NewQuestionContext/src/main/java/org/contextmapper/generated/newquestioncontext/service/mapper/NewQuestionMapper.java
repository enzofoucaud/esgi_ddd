package org.contextmapper.generated.newquestioncontext.service.mapper;

import org.contextmapper.generated.newquestioncontext.domain.NewQuestion;
import org.contextmapper.generated.newquestioncontext.domain.ResourceId;
import org.contextmapper.generated.newquestioncontext.service.dto.NewQuestionDTO;
import org.contextmapper.generated.newquestioncontext.service.dto.ResourceIdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NewQuestion} and its DTO {@link NewQuestionDTO}.
 */
@Mapper(componentModel = "spring")
public interface NewQuestionMapper extends EntityMapper<NewQuestionDTO, NewQuestion> {
    @Mapping(target = "resourceId", source = "resourceId", qualifiedByName = "resourceIdId")
    NewQuestionDTO toDto(NewQuestion s);

    @Named("resourceIdId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ResourceIdDTO toDtoResourceIdId(ResourceId resourceId);
}
