package org.contextmapper.generated.newquestioncontext.service.mapper;

import org.contextmapper.generated.newquestioncontext.domain.NotifiedUsers;
import org.contextmapper.generated.newquestioncontext.domain.UserWithPreferencesId;
import org.contextmapper.generated.newquestioncontext.service.dto.NotifiedUsersDTO;
import org.contextmapper.generated.newquestioncontext.service.dto.UserWithPreferencesIdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserWithPreferencesId} and its DTO {@link UserWithPreferencesIdDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserWithPreferencesIdMapper extends EntityMapper<UserWithPreferencesIdDTO, UserWithPreferencesId> {
    @Mapping(target = "notifiedUsers", source = "notifiedUsers", qualifiedByName = "notifiedUsersId")
    UserWithPreferencesIdDTO toDto(UserWithPreferencesId s);

    @Named("notifiedUsersId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    NotifiedUsersDTO toDtoNotifiedUsersId(NotifiedUsers notifiedUsers);
}
