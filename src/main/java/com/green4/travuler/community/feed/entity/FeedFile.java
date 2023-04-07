package com.green4.travuler.community.feed.entity;

import com.green4.travuler.community.common.dto.FileDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FeedFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_file_id")
    private Long id;

    @Column(nullable = false)
    private String originFileName;      //원본 파일명

    @Column(nullable = false)
    private String savedFileName;       //저장된 파일명

    private String uploadDir;           //경로명

    private String extension;           //확장자

    private Long size;                  //파일 사이즈

    private String contentType;         //ContentType

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed; // feed와 연관관계 매핑

    @Builder
    public FeedFile(Long id, String originFileName, String savedFileName, String uploadDir
            , String extension, Long size, String contentType){
        this.id = id;
        this.originFileName = originFileName;
        this.savedFileName = savedFileName;
        this.uploadDir = uploadDir;
        this.extension = extension;
        this.size = size;
        this.contentType = contentType;
    }

    public static FeedFile creatFeedFile(FileDto fileDto){
        FeedFile feedFile = new FeedFile();
        feedFile.setOriginFileName(fileDto.getOriginFileName());
        feedFile.setSavedFileName(fileDto.getSavedFileName());
        feedFile.setUploadDir(fileDto.getUploadDir());
        feedFile.setExtension(fileDto.getExtension());
        feedFile.setSize(fileDto.getSize());
        feedFile.setContentType(fileDto.getContentType());
        return feedFile;
    }
}
