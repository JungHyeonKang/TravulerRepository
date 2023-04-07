package com.green4.travuler.community.feed.dto;

import com.green4.travuler.community.feed.entity.FeedComment;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
public class FeedCommentListDto {
    private Long feedCommentId; //피드 댓글 pk
    private Long feedUserId; // 유저 id

    private String userName; //유저 이름
    private String comment; // 댓글 내용

    private Long parentId;
    
    private String parentUserName; // 부모댓글 쓴 사람

    private List<FeedCommentListDto> children = new ArrayList<>();

    private int commentCount;

    private int likeCount; // 좋아유 수

    private List<Long> feedCommentLikeUsers = new ArrayList<>(); // 좋아유 누른 사람덜

    public FeedCommentListDto(FeedComment feedComment) {
        this.feedCommentId = feedComment.getId();
        this.feedUserId = feedComment.getFeedUser().getId();
        this.comment = feedComment.getComment();
        this.userName = feedComment.getFeedUser().getName();
        this.likeCount = feedComment.getFeedCommentLikes().size();
        this.feedCommentLikeUsers = feedComment.getFeedCommentLikes().stream().map(commentLike -> commentLike.getFeedUser().getId()).collect(toList());
    }
}
