package com.green4.travuler.community.feed.service;

import com.green4.travuler.community.feed.dto.FeedDto;
import com.green4.travuler.community.feed.dto.FeedForm;
import com.green4.travuler.community.feed.dto.FeedSearch;
import com.green4.travuler.community.feed.entity.Feed;
import com.green4.travuler.community.feed.entity.FeedLike;
import com.green4.travuler.community.feed.repository.FeedRepository;
import com.green4.travuler.community.tag.entity.FeedHashTag;
import com.green4.travuler.community.tag.entity.HashTag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional
class FeedServiceTest {

    @Autowired
    private FeedService feedService;
    @Autowired
    private FeedUserService feedUserService;
    @Autowired
    private FeedRepository feedRepository;
    @Test
    @Transactional
    @Rollback(value = false)
    public void 피드등록() throws Exception {
        //given

        //when

        for (int i=0 ; i<=100;i++){
            long random =(int)(Math.random()*100)+1;
            FeedForm feedForm = new FeedForm();
            feedForm.setContent("테스트" + random);
            feedForm.setFeedUserId(random);

            FeedDto feedDto = new FeedDto(feedForm );
            feedService.saveFeed(feedDto);
        }


        //then

    }
    @Test
    public void 피드전체조회() throws Exception {
        //given
        FeedSearch feedSearch = new FeedSearch();

        Page<Feed> allFeeds = feedRepository.findAllFeeds(PageRequest.of(0, 10), feedSearch);
        for (Feed feed :allFeeds){
            System.out.println("피드 :  "+feed);
            for(FeedLike feedLike : feed.getFeedLikes()){
                System.out.println("피드좋아유 :  "+feedLike.getFeedUser().getId());
                System.out.println("피드좋아유 사이즈:  "+feed.getFeedLikes().size());
            }
        }
        //when
        //allFeeds.stream().forEach(feed -> feed.getFeedImages().stream().forEach(feedImage -> System.out.println(feedImage)));
    
        //then
        
    }
    @Test
    public void 피드와태그등록() throws Exception {
        //given

        List<String> hashtags = new ArrayList<>();
        HashTag hashTag = new HashTag();
        hashTag.setContent("테스트");
        hashtags.add(hashTag.getContent());

        FeedForm feedForm = new FeedForm();
        feedForm.setContent("태그테스트");
        feedForm.setFeedUserId(1L);
        feedForm.setFeedHashTags(hashtags);

        FeedDto feedDto = new FeedDto(feedForm );

        ;
        //when
        feedService.saveFeed(feedDto);
    
        //then
        
    }


}