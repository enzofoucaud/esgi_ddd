package org.contextmapper.generated.newquestioncontext.service.mapper;

import org.contextmapper.generated.newquestioncontext.domain.AddPreferencesCommand;
import org.contextmapper.generated.newquestioncontext.domain.UserPreferencesTagInfos;
import org.contextmapper.generated.newquestioncontext.service.dto.AddPreferencesCommandDTO;
import org.contextmapper.generated.newquestioncontext.service.dto.UserPreferencesTagInfosDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AddPreferencesCommand} and its DTO {@link AddPreferencesCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface AddPreferencesCommandMapper extends EntityMapper<AddPreferencesCommandDTO, AddPreferencesCommand> {
    @Mapping(target = "preferences", source = "preferences", qualifiedByName = "userPreferencesTagInfosId")
    AddPreferencesCommandDTO toDto(AddPreferencesCommand s);

    @Named("userPreferencesTagInfosId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserPreferencesTagInfosDTO toDtoUserPreferencesTagInfosId(UserPreferencesTagInfos userPreferencesTagInfos);
}
