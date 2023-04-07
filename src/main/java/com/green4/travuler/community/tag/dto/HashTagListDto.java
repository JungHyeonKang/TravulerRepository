package com.green4.travuler.community.tag.dto;

import com.green4.travuler.community.tag.entity.HashTag;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class HashTagListDto {
    private Long id;
    private String content;
    private int feedCount;

    public HashTagListDto(HashTag hashTag) {
        this.id = hashTag.getId();
        this.content = hashTag.getContent();
        this.feedCount = hashTag.getFeedHashTags().size();
    }
}
