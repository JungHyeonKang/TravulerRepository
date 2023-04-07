
package com.green4.travuler.community.common.service;

import com.green4.travuler.community.common.dto.FileDto;
import com.green4.travuler.community.feed.repository.FeedFileRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FileService {
    @Value("${upload.path}")
    private String uploadDir;


    private final FeedFileRepository feedFileRepository;

    @Transactional
    public List<FileDto> saveFile(List<MultipartFile> multipartFile) throws Exception {

        //결과 Map
        Map<String, Object> result = new HashMap<>();

        //파일 시퀀스 리스트
        List<FileDto> saveFiles = new ArrayList<>();

        try {
            if (multipartFile != null) {
                if (multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {
                    for (MultipartFile file1 : multipartFile) {
                        String originalFileName = file1.getOriginalFilename();    //오리지날 파일명
                        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));    //파일 확장자
                        String savedFileName = UUID.randomUUID() + extension;    //저장될 파일 명

                        File targetFile = new File(uploadDir + savedFileName);

                        //초기값으로 fail 설정
                        result.put("result", "FAIL");

                        FileDto fileDto = FileDto.builder()
                                .originFileName(originalFileName)
                                .savedFileName(savedFileName)
                                .uploadDir(uploadDir)
                                .extension(extension)
                                .size(file1.getSize())
                                .contentType(file1.getContentType())
                                .build();

                        try {
//                            InputStream fileStream = file1.getInputStream();
//
//                           FileUtils.copyInputStreamToFile(fileStream, targetFile); //파일 저장
                            file1.transferTo(targetFile);
                            //배열에 담기
                            saveFiles.add(fileDto);
                            result.put("fileIdxs", saveFiles.toString());
                            result.put("result", "OK");
                        } catch (Exception e) {
                            //파일삭제
                           // FileUtils.deleteQuietly(targetFile);    //저장된 현재 파일 삭제
                            e.printStackTrace();
                            result.put("result", "FAIL");
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saveFiles;
    }

}

