package com.primespark.userservice.repository;

import com.primespark.userservice.entity.LoginHistory;
import com.primespark.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
}
