package com.green4.travuler.community.feed.dto;

import lombok.Data;

@Data
public class FeedCommentLikeForm {
    private Long feedCommentId;
    private Long feedUserId;
}
