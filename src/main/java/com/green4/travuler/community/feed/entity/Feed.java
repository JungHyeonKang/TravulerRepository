package com.green4.travuler.community.feed.entity;

import com.green4.travuler.community.tag.entity.FeedHashTag;
import com.green4.travuler.community.tag.entity.HashTag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@ToString(of = {"id", "content"})
public class Feed extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_user_id")
    private FeedUser feedUser; //회원

    private String content; // 피드 내용

    @OneToMany(mappedBy = "feed",cascade = ALL)
    private List<FeedFile> feedFiles = new ArrayList<>(); //feed 이미지

    @OneToMany(mappedBy = "feed",cascade = ALL)
    private List<FeedHashTag> feedHashTags = new ArrayList<>();// 피드 해시태그

    @OneToMany(mappedBy = "feed")
    private List<FeedComment> feedComments = new ArrayList<>(); // 피드 댓글


    @OneToMany(mappedBy = "feed")
    private List<FeedLike> feedLikes = new ArrayList<>(); // 피드 좋아요


    //생성메서드
    public static Feed createFeed(String content,FeedUser feedUser,List<FeedFile> feedFiles,List<FeedHashTag> feedHashTags){
        Feed feed = new Feed();
        feed.setFeedUser(feedUser);
        feed.setContent(content);
        for (FeedFile feedFile : feedFiles){
            feed.addFeedFile(feedFile);
        }
        for (FeedHashTag feedHashTag : feedHashTags){
            feed.addHashTag(feedHashTag);
        }
        return feed;
    }
    //연관관계 메서드
    public void addFeedFile(FeedFile feedFile){
        this.feedFiles.add(feedFile);
        feedFile.setFeed(this);
    }
    public void addHashTag(FeedHashTag feedHashTag){
       this.feedHashTags.add(feedHashTag);
       feedHashTag.setFeed(this);
    }

}
