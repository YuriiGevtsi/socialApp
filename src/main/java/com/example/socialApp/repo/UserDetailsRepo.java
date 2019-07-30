package com.example.socialApp.repo;

import com.example.socialApp.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDetailsRepo extends JpaRepository<User,String> {
    @EntityGraph(attributePaths = {"subscriptions","subscribers"})
    Optional<User> findById(String s);

    List<User> findByPublicAccountTrue();
}
