package com.example.socialApp.service;

import com.example.socialApp.domain.User;
import com.example.socialApp.domain.UserSubscription;
import com.example.socialApp.repo.UserDetailsRepo;
import com.example.socialApp.repo.UserSubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    private final UserDetailsRepo userDetailsRepo;
    private final UserSubscriptionRepo userSubscriptionRepo;

    @Autowired
    public ProfileService(
            UserDetailsRepo userDetailsRepo,
            UserSubscriptionRepo userSubscriptionRepo
    ) {
        this.userDetailsRepo = userDetailsRepo;
        this.userSubscriptionRepo = userSubscriptionRepo;
    }

    public User changeSubscription(User channel, User subscriber) {
        List<UserSubscription> subscriptions = channel.getSubscribers()
                .stream()
                .filter(subscription -> subscription.getSubscriber().equals(subscriber))
                .collect(Collectors.toList());

        if (subscriptions.isEmpty()){
            UserSubscription subscription = new UserSubscription(channel, subscriber);
            channel.getSubscribers().add(subscription);
        }else {
            channel.getSubscribers().removeAll(subscriptions);
        }
        return userDetailsRepo.save(channel);
    }

    public List<UserSubscription> getSubscribers(User channel) {
        return userSubscriptionRepo.findByChannel(channel);
    }

    public UserSubscription changeSubscriptionStatus(User channel, User subscriber) {
        UserSubscription subscription = userSubscriptionRepo.findByChannelAndSubscriber(channel,subscriber);
        subscription.setActive(!subscription.isActive());

        return userSubscriptionRepo.save(subscription);
    }

    public User changeAccountStatus(User user) {
        user.setPublicAccount(!user.isPublicAccount());
        return userDetailsRepo.save(user);
    }
}
