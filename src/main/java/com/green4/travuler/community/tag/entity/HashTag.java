package com.green4.travuler.community.tag.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id","content"})
public class HashTag {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "hashtag_id")
    private Long id;

    private String content; // 해시태그 내용

    @OneToMany(mappedBy = "hashTag")
    private List<FeedHashTag> feedHashTags = new ArrayList<>();

    public static HashTag createHashTag(String content){
        HashTag hashTag = new HashTag();
        hashTag.setContent(content);
        return hashTag;
    }

}
