package com.green4.travuler.community.feed.service;

import com.green4.travuler.community.feed.dto.FeedCommentDto;
import com.green4.travuler.community.feed.dto.FeedCommentListDto;
import com.green4.travuler.community.feed.dto.FeedListDto;
import com.green4.travuler.community.feed.entity.Feed;
import com.green4.travuler.community.feed.entity.FeedComment;
import com.green4.travuler.community.feed.entity.FeedUser;
import com.green4.travuler.community.feed.repository.FeedCommentRepository;
import com.green4.travuler.community.feed.repository.FeedRepository;
import com.green4.travuler.community.feed.repository.FeedUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FeedCommentService {

    private final FeedCommentRepository feedCommentRepository;
    private final FeedUserRepository feedUserRepository;
    private final FeedRepository feedRepository;

    @Transactional
    public void saveFeedComment(FeedCommentDto feedCommentDto){ // 피드 댓글 등록
        Optional<FeedUser> findFeedUser = feedUserRepository.findById(feedCommentDto.getFeedUserId()); // 댓글 작성자 조회
        
        Optional<Feed> findFeed = feedRepository.findById(feedCommentDto.getFeedId()); // 피드 조회

        FeedComment feedComment = FeedComment.createFeedComment(feedCommentDto.getComment(), findFeed.get(), findFeedUser.get()); //댓글 엔티티 생성

        if(feedCommentDto.getParentId() !=null){ // 대댓글일 경우 댓글엔티티에 부모 댓글 정보 업데이트

            Optional<FeedComment> parent = feedCommentRepository.findById(feedCommentDto.getParentId());

            feedComment.updateParent(parent.get());
        }
        feedCommentRepository.save(feedComment);
    }
    
    public Page<FeedCommentListDto> getFeedCommentList(Long feedId,Pageable pageable){

        Page<FeedComment> feedComments = feedCommentRepository.getFeedComments(feedId,pageable);

        Map<Long, FeedCommentListDto> map = new HashMap<>();

        List<FeedCommentListDto> feedCommentListDtoList = new ArrayList<>();

        feedComments.forEach(comment->{

            FeedCommentListDto dto = new FeedCommentListDto(comment);

                    if(comment.getParent() != null){
                        Long parentId =comment.getParent().getId();

                        FeedComment findParent = feedCommentRepository.findFeedCommentById(parentId);

                        dto.setParentId(parentId);

                        dto.setParentUserName(findParent.getFeedUser().getName());
                        log.info("dto : " + dto);
                    }
            map.put(dto.getFeedCommentId(),dto);

            if(comment.getParent() != null){
                log.info(" 현재 댓글 부모 댓글  : " +  map.get(comment.getParent().getId()));
                map.get(comment.getParent().getId()).getChildren().add(dto);
            }else{
                FeedCommentListDto feedCommentListDto = map.get(dto.getFeedCommentId());

                List<FeedComment> list = feedCommentRepository.findFeedCommentsByParentId(feedCommentListDto.getFeedCommentId());

                int commentCount = retrieveArrayContents(list, 0);

                dto.setCommentCount(commentCount);

                feedCommentListDtoList.add(dto);
            }

        });

        return new PageImpl<>(feedCommentListDtoList,feedComments.getPageable(),feedComments.getTotalElements());
    }
    public static int retrieveArrayContents(List<FeedComment> list,int count) {
        count +=list.size();
        for (FeedComment element : list) {
            if (element.getChildren().size()>0) {
                count= retrieveArrayContents(element.getChildren(),count);
            } else {
            }
        }
        return count;
    }

}
