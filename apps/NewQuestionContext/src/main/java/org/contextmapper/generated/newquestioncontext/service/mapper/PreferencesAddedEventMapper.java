package org.contextmapper.generated.newquestioncontext.service.mapper;

import org.contextmapper.generated.newquestioncontext.domain.PreferencesAddedEvent;
import org.contextmapper.generated.newquestioncontext.domain.UserPreferences;
import org.contextmapper.generated.newquestioncontext.service.dto.PreferencesAddedEventDTO;
import org.contextmapper.generated.newquestioncontext.service.dto.UserPreferencesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PreferencesAddedEvent} and its DTO {@link PreferencesAddedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface PreferencesAddedEventMapper extends EntityMapper<PreferencesAddedEventDTO, PreferencesAddedEvent> {
    @Mapping(target = "preferences", source = "preferences", qualifiedByName = "userPreferencesId")
    PreferencesAddedEventDTO toDto(PreferencesAddedEvent s);

    @Named("userPreferencesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserPreferencesDTO toDtoUserPreferencesId(UserPreferences userPreferences);
}
