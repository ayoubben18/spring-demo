package com.crud.demo;

import java.time.LocalDateTime;

public record ApiErrorResponse(int status,
                               String message,
                               LocalDateTime timestamp) {
}
