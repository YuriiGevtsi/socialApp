package com.example.socialApp.repo;

import com.example.socialApp.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message,Long> {
}
