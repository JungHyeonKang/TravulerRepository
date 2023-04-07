package com.green4.travuler.community.feed.dto;

import com.green4.travuler.community.feed.entity.FeedLike;
import lombok.Data;

@Data
public class FeedLikeListDto {
    private Long feedUserId;
    private Long feedId;
    private Long feedLikeId;

    public FeedLikeListDto(FeedLike feedLike) {
        this.feedUserId = feedLike.getFeedUser().getId();
        this.feedId = feedLike.getFeed().getId();
        this.feedLikeId = feedLike.getId();
    }
}
