package com.green4.travuler.community.feed.dto;

import com.green4.travuler.community.feed.entity.FeedFile;
import lombok.Data;

@Data

public class FeedFileDto {
    private Long id;
    private String originFileName;      //원본 파일명

    private String savedFileName;       //저장된 파일명

    private String uploadDir;           //경로명

    private String extension;           //확장자

    private Long size;                  //파일 사이즈

    private String contentType;         //ContentType

    private String fullName;
    public FeedFileDto(FeedFile feedFile) {
        this.id= feedFile.getId();
        this.originFileName = feedFile.getOriginFileName();
        this.savedFileName = feedFile.getSavedFileName();
        this.uploadDir = feedFile.getUploadDir();
        this.extension = feedFile.getExtension();
        this.size = feedFile.getSize();
        this.contentType = feedFile.getContentType();
        this.fullName = feedFile.getUploadDir() + feedFile.getSavedFileName();
    }
}
