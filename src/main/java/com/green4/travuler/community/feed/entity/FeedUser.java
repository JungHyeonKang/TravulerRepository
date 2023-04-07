package com.green4.travuler.community.feed.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FeedUser { //(일단은 피드 전용 회원을 만들어서 사용하고 나중에 다른 팀원 회원 테이블이랑 합칠예정)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_user_id")
    private Long id; //유저 id

    private String name; //유저 이름

//    @OneToMany(mappedBy = "feedUser")
//    private List<Feed> feeds = new ArrayList<>();
//
//    @OneToMany(mappedBy = "feedUser")
//    private List<FeedComment> feedComments  = new ArrayList<>();
//
//    @OneToMany(mappedBy = "feedUser")
//    private List<FeedLikes> feedLikes  = new ArrayList<>();

}
