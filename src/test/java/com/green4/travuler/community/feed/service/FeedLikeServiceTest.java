package com.green4.travuler.community.feed.service;

import com.green4.travuler.community.feed.entity.Feed;
import com.green4.travuler.community.feed.entity.FeedLike;
import com.green4.travuler.community.feed.entity.FeedUser;
import com.green4.travuler.community.feed.repository.FeedLikesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FeedLikeServiceTest {
    @Autowired
    private FeedLikesRepository feedLikesRepository;

    @Autowired
    private FeedLikeService feedLikeService;

    @Test
    public void 피드좋아요조회() throws Exception {
        //given
        FeedUser feedUser = new FeedUser();
        feedUser.setId(3L);
        Feed feed = new Feed();
        feed.setId(1120L);

        //when

       // Optional<FeedLike> like = feedLikesRepository.findByFeedUserAndFeed(feedUser, feed);
        //then
      // System.out.println("findLike : " + like.isPresent());
    }
    @Test
    @Rollback(value = false)
    public void 피드좋아요_등록() throws Exception {
        //given
        FeedUser feedUser = new FeedUser();
        feedUser.setId(3L);
        Feed feed = new Feed();
        feed.setId(120L);
        FeedLike feedLike = FeedLike.createFeedLike(feedUser, feed);

        //when

        feedLikesRepository.save(feedLike);
        //then

    }
    @Test
    @Rollback(value = false)
    public void 좋아요버튼클릭() throws Exception {
        //given
        FeedUser feedUser = new FeedUser();
        feedUser.setId(3L);
        Feed feed = new Feed();
        feed.setId(100L);

        //when
        //feedLikeService.addAndDeleteLike(feedUser,feed);

        //then

    }
    @Test
    public void 한피드의피드라이크조회() throws Exception {
        //given


        //when

        List<FeedLike> findFeed = feedLikesRepository.findByFeedId(273L);

        //then
        for (FeedLike feedLike: findFeed){
            System.out.println("feedLike " + feedLike.getFeedUser().getId());
        }

    }
}