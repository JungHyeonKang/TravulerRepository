package com.green4.travuler.community.feed.dto;

import lombok.Data;

@Data
public class FeedCommentForm { // 피드 댓글 등록 dto

    private String feedCommentText; //댓글내용

    private Long feedUserId; // 피드 작성자 id

    private Long feedId; // 피드 id

    private Long parentId;
}
