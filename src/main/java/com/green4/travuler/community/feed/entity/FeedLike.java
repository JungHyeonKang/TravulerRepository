package com.green4.travuler.community.feed.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class FeedLike {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_user_id")
    private FeedUser feedUser;

    public static FeedLike createFeedLike(FeedUser feedUser,Feed feed){
        FeedLike feedLike = new FeedLike();
        feedLike.setFeed(feed);
        feedLike.setFeedUser(feedUser);
        return feedLike;
    }
}
