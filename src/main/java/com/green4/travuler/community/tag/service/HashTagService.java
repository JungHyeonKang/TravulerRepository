package com.green4.travuler.community.tag.service;

import com.green4.travuler.community.tag.dto.HashTagListDto;
import com.green4.travuler.community.tag.entity.HashTag;
import com.green4.travuler.community.tag.repostitory.HashTagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class HashTagService {

    private final HashTagRepository hashTagRepository;

    public void saveHashTag(HashTag hashTag){
        hashTagRepository.save(hashTag);
    }
    public Page<HashTagListDto> getHashTags(Pageable pageable , String content){

        Page<HashTag> hashTags = hashTagRepository.getHashTags(pageable,content);

        Page<HashTagListDto> hashTagListDtos = hashTags.map(hashTag -> new HashTagListDto(hashTag));

        return  hashTagListDtos;
    }
}
