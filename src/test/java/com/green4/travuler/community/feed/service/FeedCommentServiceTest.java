package com.green4.travuler.community.feed.service;

import com.green4.travuler.community.feed.dto.FeedCommentDto;
import com.green4.travuler.community.feed.entity.Feed;
import com.green4.travuler.community.feed.entity.FeedComment;
import com.green4.travuler.community.feed.entity.FeedUser;
import com.green4.travuler.community.feed.repository.FeedCommentRepository;
import com.green4.travuler.community.feed.repository.FeedRepository;
import com.green4.travuler.community.feed.repository.FeedUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class FeedCommentServiceTest {
    @Autowired
    private FeedCommentService feedCommentService;
    @Autowired
    private FeedUserRepository feedUserRepository;
    @Autowired
    private FeedRepository feedRepository;
    @Autowired
    private FeedCommentRepository feedCommentRepository;
    
    @Test
    @Transactional
    @Rollback(value = false)
    public void 피드댓글등록() throws Exception {
        //given
        Feed feed = new Feed();
        feed.setId(3L);
        Optional<Feed> findFeed = feedRepository.findById(feed.getId());
        FeedUser feedUser = new FeedUser();
        feedUser.setId(1L);
        Optional<FeedUser> findUser = feedUserRepository.findById(feedUser.getId());
        FeedComment feedComment = new FeedComment();

        feedComment.setComment("하이2");
        feedComment.setFeed(findFeed.get());
        feedComment.setFeedUser(findUser.get());


        //when
        feedCommentRepository.save(feedComment);
    
        //then
        
    }
    @Test
    @Transactional
    @Rollback(value = false)
    public void 피드대댓글테스트() throws Exception {
        //given
        Feed feed = new Feed();
        feed.setId(631L);
        Optional<Feed> findFeed = feedRepository.findById(feed.getId());
        FeedUser feedUser = new FeedUser();
        feedUser.setId(2L);
        Optional<FeedUser> findUser = feedUserRepository.findById(feedUser.getId());
        FeedComment feedComment = new FeedComment();
        FeedComment feedComment2 = new FeedComment();

//        feedComment.setComment("54번 댓글 자식댓글");
//        feedComment.setFeed(findFeed.get());
//        feedComment.setFeedUser(findUser.get());

        Optional<FeedComment> findComment = feedCommentRepository.findById(202L);

        System.out.println("findComment : " + findComment);

        feedComment2.setComment("631번 자식댓글");
        feedComment2.setFeed(findFeed.get());
        feedComment2.setFeedUser(findUser.get());
        feedComment2.setParent(findComment.get());



        //when
        feedCommentRepository.save(feedComment2);

        //then

    }
}