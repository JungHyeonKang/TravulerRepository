package com.green4.travuler.community.feed.service;

import com.green4.travuler.community.feed.dto.FeedLikeForm;
import com.green4.travuler.community.feed.dto.FeedLikeListDto;
import com.green4.travuler.community.feed.dto.FeedListDto;
import com.green4.travuler.community.feed.entity.Feed;
import com.green4.travuler.community.feed.entity.FeedLike;
import com.green4.travuler.community.feed.entity.FeedUser;
import com.green4.travuler.community.feed.repository.FeedLikesRepository;
import com.green4.travuler.community.feed.repository.FeedRepository;
import com.green4.travuler.community.feed.repository.FeedUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FeedLikeService {

    private final FeedLikesRepository feedLikesRepository;
    private final FeedUserRepository feedUserRepository;
    private final FeedRepository feedRepository;

    public List<FeedLikeListDto> addAndDeleteLike(FeedLikeForm feedLikeForm){

        Optional<FeedLike> findFeedLike = isNotAlreadyLike(feedLikeForm);

        Optional<FeedUser> findUser = feedUserRepository.findById(feedLikeForm.getFeedUserId());

        Optional<Feed> findFeed = feedRepository.findById(feedLikeForm.getFeedId());

        if(!findFeedLike.isPresent()){

            FeedLike feedLike = FeedLike.createFeedLike(findUser.get(), findFeed.get());

            feedLikesRepository.save(feedLike);

        }else{

            feedLikesRepository.delete(findFeedLike.get());

        }
        List<FeedLike> feedLikeList = feedLikesRepository.findByFeedId(feedLikeForm.getFeedId());

        List<FeedLikeListDto> resultList = feedLikeList.stream().map(FeedLikeListDto::new).collect(toList());
        return resultList;

    }

    public Optional<FeedLike> isNotAlreadyLike(FeedLikeForm feedLikeForm){
        Optional<FeedLike> findLike = feedLikesRepository.findByFeedUserIdAndFeedId(feedLikeForm.getFeedUserId(), feedLikeForm.getFeedId());

        return findLike;
    }
}
