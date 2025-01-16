package com.example.KudiSave.domain.mapper;

import com.example.KudiSave.domain.models.KudiUser;
import com.example.KudiSave.infrastructure.inputs.data.responses.SignUpResponse;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface KudiSaveServiceMapper {
   SignUpResponse toSignUpResponse(KudiUser kudiUser);

}
