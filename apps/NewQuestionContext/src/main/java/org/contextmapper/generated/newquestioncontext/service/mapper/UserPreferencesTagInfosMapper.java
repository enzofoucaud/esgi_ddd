package org.contextmapper.generated.newquestioncontext.service.mapper;

import org.contextmapper.generated.newquestioncontext.domain.UserPreferences;
import org.contextmapper.generated.newquestioncontext.domain.UserPreferencesTagInfos;
import org.contextmapper.generated.newquestioncontext.service.dto.UserPreferencesDTO;
import org.contextmapper.generated.newquestioncontext.service.dto.UserPreferencesTagInfosDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserPreferencesTagInfos} and its DTO {@link UserPreferencesTagInfosDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserPreferencesTagInfosMapper extends EntityMapper<UserPreferencesTagInfosDTO, UserPreferencesTagInfos> {
    @Mapping(target = "userPreferences", source = "userPreferences", qualifiedByName = "userPreferencesId")
    UserPreferencesTagInfosDTO toDto(UserPreferencesTagInfos s);

    @Named("userPreferencesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserPreferencesDTO toDtoUserPreferencesId(UserPreferences userPreferences);
}
