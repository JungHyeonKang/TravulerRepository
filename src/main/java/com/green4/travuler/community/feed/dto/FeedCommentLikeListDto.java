package com.green4.travuler.community.feed.dto;

import com.green4.travuler.community.feed.entity.FeedCommentLike;
import com.green4.travuler.community.feed.entity.FeedLike;
import lombok.Data;

@Data
public class FeedCommentLikeListDto {
    private Long feedUserId;
    private Long feedCommentId;
    private Long feedCommentLikeId;

    public FeedCommentLikeListDto(FeedCommentLike feedCommentLike) {
        this.feedUserId = feedCommentLike.getFeedUser().getId();
        this.feedCommentId = feedCommentLike.getFeedComment().getId();
        this.feedCommentLikeId = feedCommentLike.getId();
    }
}
