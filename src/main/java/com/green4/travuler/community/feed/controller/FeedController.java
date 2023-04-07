package com.green4.travuler.community.feed.controller;

import com.green4.travuler.community.feed.dto.FeedForm;
import com.green4.travuler.community.feed.dto.FeedDto;
import com.green4.travuler.community.feed.dto.FeedListDto;
import com.green4.travuler.community.feed.dto.FeedSearch;
import com.green4.travuler.community.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/feed")
public class FeedController {

    private final FeedService feedService;

    @GetMapping("/list") //피드 전체 조회 컨트롤러
    public Page<FeedListDto> getFeedAll(Pageable pageable, FeedSearch feedSearch) {
        log.info("feedSearch : " + feedSearch);
        log.info("pageable : " + pageable);

        Page<FeedListDto> allFeeds = feedService.getAllFeeds(pageable,feedSearch);

        return allFeeds;
    }
    @PostMapping(value = "/register") // 피드 등록 컨트롤러
    public ResponseEntity createFeed( FeedForm feedForm) throws Exception {
        log.info("createFeedDto : "+ feedForm);
        //FeedDto feedDto = createFeedDto.parseFeedDto(createFeedDto);
        FeedDto feedDto = new FeedDto(feedForm);

        feedService.saveFeed(feedDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
