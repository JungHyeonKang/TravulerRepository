package com.green4.travuler.community.feed.controller;

import com.green4.travuler.community.feed.dto.FeedCommentLikeForm;
import com.green4.travuler.community.feed.dto.FeedCommentLikeListDto;
import com.green4.travuler.community.feed.dto.FeedLikeForm;
import com.green4.travuler.community.feed.dto.FeedLikeListDto;
import com.green4.travuler.community.feed.entity.FeedCommentLike;
import com.green4.travuler.community.feed.service.FeedCommentLikeService;
import com.green4.travuler.community.feed.service.FeedLikeService;
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
@RequestMapping("/commentLike")
@Slf4j
@RequiredArgsConstructor
public class FeedCommentLikeController {
    private final FeedCommentLikeService feedCommentLikeService;

    @PostMapping("/changeCommentLike")
    public ResponseEntity addAndDeleteLike(@RequestBody FeedCommentLikeForm feedCommentLikeForm){
        log.info("feedCommentLikeForm : " + feedCommentLikeForm);

        List<FeedCommentLikeListDto> result = feedCommentLikeService.addAndDeleteLike(feedCommentLikeForm);

        return new ResponseEntity(result, HttpStatus.OK);
    }
}
