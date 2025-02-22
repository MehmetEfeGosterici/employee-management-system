package com.gosterici.adesso.userservice.service.ports;

import java.util.UUID;

public interface DeleteUserPort {

    void deleteUser(UUID userId);
}
