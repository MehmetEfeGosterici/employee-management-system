package com.gosterici.adesso.userservice.controller;

import com.gosterici.adesso.userservice.domain.User;
import com.gosterici.adesso.userservice.domain.request.UpdateUserRequest;
import com.gosterici.adesso.userservice.domain.resource.UserResource;
import com.gosterici.adesso.userservice.service.mapper.UserMapper;
import com.gosterici.adesso.userservice.service.ports.DeleteUserPort;
import com.gosterici.adesso.userservice.service.ports.UpdateUserPort;
import com.gosterici.adesso.userservice.service.ports.UserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserQuery userQuery;
    private final UpdateUserPort updateUserPort;
    private final DeleteUserPort deleteUserPort;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResource> getUser(@PathVariable UUID userId){
        Optional<User> optionalUser = userQuery.getUserById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            UserResource userResource = UserMapper.INSTANCE.mapUserToUserResource(user);
            return ResponseEntity.ok(userResource);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResource>> getUsers(@RequestBody List<UUID> userIds){
        List<User> userList = userQuery.getUsers(userIds);
        List<UserResource> userResourceList = userList.stream().map(UserMapper.INSTANCE::mapUserToUserResource).toList();
        return ResponseEntity.ok(userResourceList);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<List<UserResource>> getAllUsers(){
        List<User> userList = userQuery.getAllUsers();
        List<UserResource> userResourceList = userList.stream().map(UserMapper.INSTANCE::mapUserToUserResource).toList();
        return ResponseEntity.ok(userResourceList);
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UpdateUserRequest updateUserRequest){
        updateUserPort.updateUser(updateUserRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId){
        deleteUserPort.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
