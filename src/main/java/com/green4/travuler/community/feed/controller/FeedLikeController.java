package com.green4.travuler.community.feed.controller;

import com.green4.travuler.community.feed.dto.FeedLikeForm;
import com.green4.travuler.community.feed.dto.FeedLikeListDto;
import com.green4.travuler.community.feed.service.FeedLikeService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feedLike")
@Slf4j
@RequiredArgsConstructor
public class FeedLikeController {

    private final FeedLikeService feedLikeService;

    @PostMapping("/changeLike")
    public ResponseEntity addAndDeleteLike(@RequestBody FeedLikeForm feedLikeForm){
        log.info("feedLikeForm : " + feedLikeForm);
        List<FeedLikeListDto> result = feedLikeService.addAndDeleteLike(feedLikeForm);

        return new ResponseEntity(result,HttpStatus.OK);
    }

}
