package com.primespark.userservice.dto;

import com.primespark.userservice.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
