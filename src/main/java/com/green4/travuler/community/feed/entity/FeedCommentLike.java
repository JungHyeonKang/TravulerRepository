package com.green4.travuler.community.feed.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class FeedCommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedComment_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_comment_id")
    private FeedComment feedComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_user_id")
    private FeedUser feedUser;

    public static FeedCommentLike createFeedCommentLike(FeedUser feedUser,FeedComment feedComment){
        FeedCommentLike feedCommentLike = new FeedCommentLike();
        feedCommentLike.setFeedComment(feedComment);
        feedCommentLike.setFeedUser(feedUser);
        return feedCommentLike;
    }

}
