package com.green4.travuler.community.feed.dto;

import com.green4.travuler.community.tag.entity.FeedHashTag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class FeedForm { //피드 등록 전용 dto

    private String content;
    private Long feedUserId;
    private List<MultipartFile> fileList = new ArrayList<>();
    private List<String> feedHashTags = new ArrayList<>();

//    public FeedDto parseFeedDto(){
//        FeedDto feedDto = new FeedDto(content,feedUserId,imageList);
//        return  feedDto;
//    }


}
