package com.green4.travuler.community.feed.dto;

import com.green4.travuler.community.feed.entity.Feed;
import com.green4.travuler.community.feed.entity.FeedUser;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class FeedCommentDto { //피드 댓글 데이터 이동 dto

    private String comment; //댓글 내용

    private Long feedUserId; // 피드 작성자 fk

    private Long feedId; // 피드 fk

    private Long parentId;
    public FeedCommentDto(FeedCommentForm feedCommentForm) {
        this.comment = feedCommentForm.getFeedCommentText();
        this.feedUserId = feedCommentForm.getFeedUserId();
        this.feedId = feedCommentForm.getFeedId();
        this.parentId=feedCommentForm.getParentId();
    }
}
