package com.app.contacts.service.mapper;

import com.app.contacts.model.User;
import com.app.contacts.model.dto.RegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User registrationRequestToUser(RegistrationRequest request);
}
