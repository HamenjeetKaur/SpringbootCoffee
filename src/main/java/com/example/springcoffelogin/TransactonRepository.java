package com.example.springcoffelogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactonRepository extends JpaRepository<User, Long> {
}