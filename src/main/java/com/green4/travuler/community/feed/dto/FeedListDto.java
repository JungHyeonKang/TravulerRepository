package com.green4.travuler.community.feed.dto;

import com.green4.travuler.community.feed.entity.Feed;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.*;

@Data
public class FeedListDto { // 피드 리스트 반환 dto
    private Long feedId; //피드 PK
    private String content; //피드 내용
    private String feedUsername; // 피드 작성자 이름
    private Long feedUserId; // 피드 작성자 pk
    private List<FeedFileDto> feedFiles; //피드 이미지 리스트
    private int likeCount; // 좋아유 수
    private List<Long> feedLikeUsers = new ArrayList<>(); // 좋아유 누른 사람덜

    private int commentCount; // 댓글 수

    public FeedListDto(Feed feed) {
        this.feedId = feed.getId();
        this.content = feed.getContent();
        this.feedUsername = feed.getFeedUser().getName();
        this.feedUserId = feed.getFeedUser().getId();
        this.feedFiles = feed.getFeedFiles().stream().map(FeedFileDto::new).collect(toList());
        this.likeCount = feed.getFeedLikes().size();
        this.commentCount = feed.getFeedComments().size();
        this.feedLikeUsers = feed.getFeedLikes().stream().map(feedLike -> feedLike.getFeedUser().getId()).collect(toList()); //비
    }
}
