package com.zenova.userservice.dto;

import com.zenova.userservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginHistoryDTO {
    private Long id;
    private LocalDateTime loginTime;
    private User user;
}
