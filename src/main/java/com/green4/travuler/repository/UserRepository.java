package com.green4.travuler.repository;

import com.green4.travuler.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
