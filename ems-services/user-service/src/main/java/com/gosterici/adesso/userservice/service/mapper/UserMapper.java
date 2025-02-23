package com.gosterici.adesso.userservice.service.mapper;

import com.gosterici.adesso.userservice.domain.Authorities;
import com.gosterici.adesso.userservice.domain.User;
import com.gosterici.adesso.userservice.domain.resource.UserResource;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mapping(target = "authorities", expression = "java(mapAuthorities(user.getAuthorities()))")
  UserResource mapUserToUserResource(User user);

  default List<String> mapAuthorities(Set<Authorities> authorities) {
    return authorities.stream().map(Authorities::getAuthority).toList();
  }
}
