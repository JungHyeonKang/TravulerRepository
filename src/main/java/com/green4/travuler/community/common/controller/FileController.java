package com.green4.travuler.community.common.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileController {

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName){
        log.info("UploadController => getFile 실행 => 받은 fileName: "+fileName);
        ResponseEntity<byte[]> result = null;
        try{
            String srcFileName = URLDecoder.decode(fileName,"UTF-8");
//            log.info("fileName: "+fileName);
            //File file = new File(uploadPath+File.separator+srcFileName);
            File file = new File(fileName);
//            log.info("File: "+file);
            HttpHeaders header = new HttpHeaders();

            // MIME 타입 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));

            // 파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header, HttpStatus.OK);
        } catch (Exception e){
//            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("result : "+result);
        return result;

    }
}
