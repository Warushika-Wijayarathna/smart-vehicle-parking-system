package com.primespark.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.primespark.userservice.util.Role;
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
}
