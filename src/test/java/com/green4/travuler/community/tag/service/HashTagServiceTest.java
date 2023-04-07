package com.green4.travuler.community.tag.service;

import com.green4.travuler.community.tag.entity.FeedHashTag;
import com.green4.travuler.community.tag.entity.HashTag;
import com.green4.travuler.community.tag.repostitory.HashTagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class HashTagServiceTest {
    @Autowired
    private HashTagService hashTagService;
    @Autowired
    private HashTagRepository hashTagRepository;

    @Test
    @Rollback(value = false)
    public void 해쉬태그등록() throws Exception {
        //given
        HashTag hashTag = new HashTag();
        hashTag.setContent("테스트3");

        //when
        hashTagRepository.save(hashTag);

        //then

    }
    @Test
    public void 해쉬태그_조회() throws Exception {
        //given

        Optional<HashTag> findHashTag = hashTagRepository.findByContent("테스트");

        //when


        //then
        System.out.println("findHashTag : " + findHashTag);
    }
    @Test
    public void 피드해쉬태그조회() throws Exception {
        //given
        PageRequest of = PageRequest.of(0, 10, Sort.unsorted());
        //when
        Page<HashTag> hashTags = hashTagRepository.getHashTags(of,"#하");

        //then
        for(HashTag hashTag : hashTags){

            List<FeedHashTag> feedHashTags = hashTag.getFeedHashTags();
            System.out.println("size" + hashTag.getFeedHashTags().size());
            for (FeedHashTag feedHashTag : feedHashTags) {
                 System.out.println("feedHashTag" + feedHashTag.getFeed());

            }
        }
        System.out.println("getTotalElements : " + hashTags.getTotalElements());
        //System.out.println("getNumberOfElements" + hashTags.get);
    }

}