package com.example.KudiSave.infrastructure.inputs.mapper;

import com.example.KudiSave.domain.models.KudiUser;
import com.example.KudiSave.infrastructure.inputs.data.requests.SignUpRequest;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface KudiUserRestMapper {
    KudiUser toKudiUser (SignUpRequest signUpRequest);
}
