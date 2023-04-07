package com.green4.travuler.community.feed.service;

import com.green4.travuler.community.feed.entity.FeedUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FeedUserServiceTest {

    @Autowired
    private FeedUserService feedUserService;

    @Test
    public void 피드유저등록() throws Exception {
        //given


        //when
        for(int i=0 ; i<100 ;i++){
            FeedUser feedUser = new FeedUser();
            feedUser.setName("회원" + i);
            feedUserService.register(feedUser);
        }


        //then

    }
}