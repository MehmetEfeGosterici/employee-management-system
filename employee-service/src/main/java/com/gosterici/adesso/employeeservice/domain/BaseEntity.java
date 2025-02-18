package com.gosterici.adesso.employeeservice.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseEntity {

    private UUID id;
    private LocalDateTime insertedAt;
    private LocalDateTime updatedAt;
}
