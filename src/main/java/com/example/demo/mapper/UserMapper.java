package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper {

    private ModelMapper mapper;

    @Autowired
    public UserMapper(ModelMapper mapper){
        this.mapper = mapper;
    }

    public User toEntity(UserDto dto){
        return Objects.isNull(dto) ? null : mapper.map(dto, User.class);
    }

    public UserDto toDto(User user) {
        return Objects.isNull(user) ? null : mapper.map(user, UserDto.class);
    }
}
