package com.Task_Board.repository;

import com.Task_Board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
      User findByUsernameAndPassword(String username, String password);
}
