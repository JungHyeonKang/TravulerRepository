package com.green4.travuler.community.tag.controller;

import com.green4.travuler.community.tag.dto.HashTagListDto;
import com.green4.travuler.community.tag.entity.HashTag;
import com.green4.travuler.community.tag.service.HashTagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hashTag")
@Slf4j
@RequiredArgsConstructor
public class HashTagController {

    private final HashTagService hashTagService;

    @GetMapping("/list/{content}")
    public Page<HashTagListDto> getHashTags(@PathVariable("content") String content,Pageable pageable){
        log.info("해쉬태그 조회ㅏ " + content);
        Page<HashTagListDto> hashTags = hashTagService.getHashTags(pageable, "#"+ content);
        return hashTags;
    }
}
