package com.green4.travuler.community.feed.controller;

import com.green4.travuler.community.feed.dto.FeedCommentDto;
import com.green4.travuler.community.feed.dto.FeedCommentForm;
import com.green4.travuler.community.feed.dto.FeedCommentListDto;
import com.green4.travuler.community.feed.entity.FeedComment;
import com.green4.travuler.community.feed.service.FeedCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/comment")
public class FeedCommentController {

    private final FeedCommentService feedCommentService;

//    @GetMapping("/list/{feedId}")
//    public Page<FeedCommentListDto>  getFeedCommentList(@PathVariable("feedId") Long feedId,Pageable pageable){
//        log.info("댓글리스트 조회 : " + feedId + " : " +pageable );
//        Page<FeedCommentListDto> feedCommentList = feedCommentService.getFeedCommentList(feedId, pageable);
////        feedCommentList.forEach(i->log.info("피드 댓글 : " + i.getComment()));
//        return feedCommentList;
//    }
    @PostMapping("/register")
    public void saveComment(@RequestBody FeedCommentForm commentForm){
        log.info("commentForm : " + commentForm);
        FeedCommentDto feedCommentDto = new FeedCommentDto(commentForm);
        feedCommentService.saveFeedComment(feedCommentDto);

    }
    @GetMapping("/list/{feedId}")
    public Page<FeedCommentListDto> getFeedCommentList(@PathVariable("feedId") Long feedId,Pageable pageable){
        //log.info("하이 " + feedId);
        Page<FeedCommentListDto> feedList = feedCommentService.getFeedCommentList(feedId, pageable);
        //feedList.forEach(i->log.info("feedcommnet : "+i));

        return feedList;
    }
}
