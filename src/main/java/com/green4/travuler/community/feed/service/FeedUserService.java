package com.green4.travuler.community.feed.service;

import com.green4.travuler.community.feed.entity.FeedUser;
import com.green4.travuler.community.feed.repository.FeedRepository;
import com.green4.travuler.community.feed.repository.FeedUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeedUserService {

    private final FeedUserRepository feedUserRepository;

    @Transactional
    public Long register(FeedUser feedUser){
        FeedUser findFeedUser = feedUserRepository.save(feedUser);
        return  findFeedUser.getId();
    }
    public Optional<FeedUser> findOne(Long id){
        Optional<FeedUser> findFeedUser = feedUserRepository.findById(id);
        return findFeedUser;
    }
}
