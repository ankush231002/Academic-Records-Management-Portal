package com.example.ankush.repository;

import com.example.ankush.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<User, Long> {

    Optional<User> findByStudentId(String studentId);

    void deleteByStudentId(String studentId);

    @Query("SELECT MAX(u.studentId) FROM User u")
    String findMaxStudentId();
}
