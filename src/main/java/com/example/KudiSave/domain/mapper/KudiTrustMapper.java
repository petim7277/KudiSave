package com.example.KudiSave.domain.mapper;

import com.example.KudiSave.infrastructure.inputs.data.requests.SignUpRequest;
import com.example.KudiSave.infrastructure.output.persistence.entity.KudiUserEntity;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface KudiTrustMapper {
   KudiUserEntity toSignUpRequest(SignUpRequest signUpRequest);

   UserRepresentation toUserRepresentation(SignUpRequest signUpRequest);
   SignUpRequest toSignUpRequest(UserRepresentation userRepresentation);
}
