package com.green4.travuler.community.feed.entity;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@ToString(of = {"id", "comment","children"})
public class FeedComment extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_comment_id")
    private Long id;

    private String comment; //댓글 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed; //피드 연관관계

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_user_id")
    private FeedUser feedUser; //회원 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private FeedComment parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<FeedComment> children = new ArrayList<>();

    @OneToMany(mappedBy = "feedComment")
    private List<FeedCommentLike> feedCommentLikes = new ArrayList<>(); // 피드 좋아요

    public static FeedComment createFeedComment(String comment,Feed feed,FeedUser feedUser){
        FeedComment feedComment = new FeedComment();
        feedComment.setComment(comment);
        feedComment.setFeedUser(feedUser);
        feedComment.setFeed(feed);

        return feedComment;
    }

    public void updateParent(FeedComment parent){
        this.parent = parent;
    }



}
