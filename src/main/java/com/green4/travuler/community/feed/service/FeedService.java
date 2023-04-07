package com.green4.travuler.community.feed.service;

import com.green4.travuler.community.common.dto.FileDto;
import com.green4.travuler.community.common.service.FileService;
import com.green4.travuler.community.feed.dto.FeedDto;
import com.green4.travuler.community.feed.dto.FeedListDto;
import com.green4.travuler.community.feed.dto.FeedSearch;
import com.green4.travuler.community.feed.entity.Feed;
import com.green4.travuler.community.feed.entity.FeedFile;
import com.green4.travuler.community.feed.entity.FeedUser;
import com.green4.travuler.community.feed.repository.FeedRepository;
import com.green4.travuler.community.feed.repository.FeedUserRepository;
import com.green4.travuler.community.tag.entity.FeedHashTag;
import com.green4.travuler.community.tag.entity.HashTag;
import com.green4.travuler.community.tag.repostitory.HashTagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FeedService {
    private final FeedRepository feedRepository;
    private final FeedUserRepository feedUserRepository;
    private final FileService fileService;

    private final HashTagRepository hashTagRepository;
    @Transactional
    public Long saveFeed(FeedDto feedDto) throws Exception { //피드 등록
        log.info("feedDto : " + feedDto );
        Optional<FeedUser> findFeedUser = feedUserRepository.findById(feedDto.getFeedUserId()); // 피드 등록한 유저 정보 조회

        List<FileDto> fileDtos = fileService.saveFile(feedDto.getFeedFiles()); // 피드 파일 서버에 저장

        List<FeedFile> feedFiles = fileDtos.stream().map(FeedFile::creatFeedFile).collect(toList()); //피드 파일 엔티티 생성

        List<FeedHashTag> feedHashTagList = new ArrayList<>();

        if(feedDto.getFeedHashTags().size() >0){ // 피드해쉬태그 엔티티 생성

            for(String content : feedDto.getFeedHashTags()){

                HashTag hashTag = HashTag.createHashTag(content);

                HashTag findHashTag = findHashTag(hashTag);

                FeedHashTag feedHashTag = FeedHashTag.createFeedHashTag(findHashTag);

                feedHashTagList.add(feedHashTag);
            }

        }

        Feed feed = Feed.createFeed(feedDto.getContent(), findFeedUser.get(), feedFiles , feedHashTagList ); // 피드 생성
        
        feedRepository.save(feed); // 피드 db 저장

        return feed.getId();
    }

    public Optional<Feed> findFeed(Long id){ // 피드 하나 찾기
        return feedRepository.findById(id);
    }

    public Page<FeedListDto> getAllFeeds(Pageable pageable, FeedSearch feedSearch){ //전체 피드 조회

        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "createdDate"));

        Page<Feed> allFeeds = feedRepository.findAllFeeds(pageRequest,feedSearch);
        for(Feed feed : allFeeds){
            log.info("test " + feed.getFeedHashTags());
            for(FeedHashTag feedHashTag :feed.getFeedHashTags() ){
                log.info("test2 " + feedHashTag);
            }
        }
        Page<FeedListDto> feedAllList = allFeeds.map(FeedListDto::new);

        return feedAllList;
    }

    private HashTag findHashTag(HashTag hashTag){

        Optional<HashTag> findHashTag = hashTagRepository.findByContent(hashTag.getContent());

        if(findHashTag.isPresent()){ // 이미 존재하는 해시태그 일 경우
            return  findHashTag.get();
        }
        else{ // 새로운 해시태그 일 경우
            HashTag savedHashTag = hashTagRepository.save(hashTag);

            return savedHashTag;
        }
    }

}
