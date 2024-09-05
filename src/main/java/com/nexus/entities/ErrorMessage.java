package com.nexus.entities;

import lombok.*;
import org.springframework.http.HttpStatus;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private HttpStatus status;
    private String message;
}
