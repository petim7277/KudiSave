package com.example.KudiSave.infrastructure.output.persistence.mapper;


import com.example.KudiSave.domain.models.KudiUser;
import com.example.KudiSave.infrastructure.output.persistence.entity.KudiUserEntity;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface KudiUserPersistenceMapper {
    @Mapping(target = "id", ignore = true)
    KudiUserEntity toKudiUserEntity(KudiUser appUserDomainObject);
    @Mapping(target = "id", ignore = true)
    KudiUser toKudiUser(KudiUserEntity appUserEntity);
    UserRepresentation toUserRepresentation(KudiUser userDomainObject);
    @Mapping(target = "id", ignore = true)
    KudiUser toAppDomainObject(UserRepresentation userRepresentation);
}
