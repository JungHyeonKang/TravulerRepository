package com.green4.travuler.community.feed.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class FeedDto { //서버 내 피드 데이터 이동 dto
    private String content;
    private Long feedUserId;
    private List<MultipartFile> feedFiles = new ArrayList<>();
    private List<String> feedHashTags = new ArrayList<>();
//    public FeedDto(String content, Long feedUserId, List<MultipartFile> feedImages) {
//        this.content = content;
//        this.feedUserId = feedUserId;
//        this.feedImages = feedImages;
//        //this.feedVideos = feedVideos;
//    }

    public FeedDto(FeedForm feedForm) {
        this.content = feedForm.getContent();
        this.feedUserId = feedForm.getFeedUserId();
        this.feedFiles = feedForm.getFileList();
        this.feedHashTags = feedForm.getFeedHashTags();
    }
}
