package com.green4.travuler.community.tag.entity;

import com.green4.travuler.community.feed.entity.Feed;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id"})
public class FeedHashTag {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "feed_hashTag_id")
    private Long id;

    @ManyToOne(fetch = LAZY )
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @ManyToOne(fetch = LAZY )
    @JoinColumn(name = "hashtag_id")
    private HashTag hashTag;

    public static FeedHashTag createFeedHashTag(HashTag hashTag){
        FeedHashTag feedHashTag = new FeedHashTag();
        feedHashTag.setHashTag(hashTag);
        return feedHashTag;
    }
}
