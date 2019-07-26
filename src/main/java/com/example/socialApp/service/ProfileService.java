package com.example.socialApp.service;

import com.example.socialApp.domain.User;
import com.example.socialApp.domain.UserSubscription;
import com.example.socialApp.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    private final UserDetailsRepo userDetailsRepo;

    @Autowired
    public ProfileService(UserDetailsRepo userDetailsRepo) {
        this.userDetailsRepo = userDetailsRepo;
    }

    public User changeSubscription(User channel, User subscriber) {
        List<UserSubscription> subscriptions = channel.getSubscribers()
                .stream()
                .filter(subscription -> subscriber.getSubscribers().equals(subscriber))
                .collect(Collectors.toList());

        if (subscriptions.isEmpty()){
            UserSubscription subscription = new UserSubscription(channel, subscriber);
            channel.getSubscribers().add(subscription);
        }else {
            channel.getSubscribers().removeAll(subscriptions);
        }
        return userDetailsRepo.save(channel);
    }
}
