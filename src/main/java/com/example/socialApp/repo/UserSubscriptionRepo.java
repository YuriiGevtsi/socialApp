package com.example.socialApp.repo;

import com.example.socialApp.domain.User;
import com.example.socialApp.domain.UserSubscription;
import com.example.socialApp.domain.UserSubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubscriptionRepo extends JpaRepository<UserSubscription, UserSubscriptionId> {
    List<UserSubscription> findBySubscriber(User user);

    UserSubscription findByChannelAndSubscriber(User channel, User subscriber);

    List<UserSubscription> findByChannel(User channel);
}
