package com.zenova.cloudgateway.dto;

import com.zenova.cloudgateway.util.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private Role role;
    private LocalDateTime createdAt;
    private boolean isActive;
}
