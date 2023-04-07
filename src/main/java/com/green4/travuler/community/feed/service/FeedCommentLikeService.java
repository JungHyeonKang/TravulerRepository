package com.green4.travuler.community.feed.service;

import com.green4.travuler.community.feed.dto.FeedCommentLikeForm;
import com.green4.travuler.community.feed.dto.FeedCommentLikeListDto;
import com.green4.travuler.community.feed.dto.FeedLikeForm;
import com.green4.travuler.community.feed.dto.FeedLikeListDto;
import com.green4.travuler.community.feed.entity.*;
import com.green4.travuler.community.feed.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FeedCommentLikeService {
    private final FeedCommentLikeRepository feedCommentLikeRepository;
    private final FeedUserRepository feedUserRepository;
    private final FeedCommentRepository feedCommentRepository;

    public List<FeedCommentLikeListDto> addAndDeleteLike(FeedCommentLikeForm feedCommentLikeForm){

        Optional<FeedCommentLike> findCommentLike = isNotAlreadyLike(feedCommentLikeForm);

        Optional<FeedUser> findUser = feedUserRepository.findById(feedCommentLikeForm.getFeedUserId());

        Optional<FeedComment> findComment = feedCommentRepository.findById(feedCommentLikeForm.getFeedCommentId());

        if(!findCommentLike.isPresent()){

            FeedCommentLike feedCommentLike = FeedCommentLike.createFeedCommentLike(findUser.get(), findComment.get());

            feedCommentLikeRepository.save(feedCommentLike);

        }else{

            feedCommentLikeRepository.delete(findCommentLike.get());

        }
        List<FeedCommentLike> feedCommentLikeList = feedCommentLikeRepository.findByFeedCommentId(feedCommentLikeForm.getFeedCommentId());

        List<FeedCommentLikeListDto> resultList = feedCommentLikeList.stream().map(FeedCommentLikeListDto::new).collect(toList());

        return resultList;

    }

    public Optional<FeedCommentLike> isNotAlreadyLike(FeedCommentLikeForm feedCommentLikeForm){

        Optional<FeedCommentLike> findCommentLike = feedCommentLikeRepository.findByFeedUserIdAndFeedCommentId(feedCommentLikeForm.getFeedUserId(), feedCommentLikeForm.getFeedCommentId());

        return findCommentLike;
    }
}
