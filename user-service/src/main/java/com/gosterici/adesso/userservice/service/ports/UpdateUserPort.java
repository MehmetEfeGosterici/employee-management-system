package com.gosterici.adesso.userservice.service.ports;

import com.gosterici.adesso.userservice.domain.request.UpdateUserRequest;

public interface UpdateUserPort {

    void updateUser(UpdateUserRequest request);
}
