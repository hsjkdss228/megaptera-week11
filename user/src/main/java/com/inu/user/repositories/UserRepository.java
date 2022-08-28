package com.inu.user.repositories;

import com.inu.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User getByEmail(String email);
}
