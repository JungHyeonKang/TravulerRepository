package com.green4.travuler.community.feed.repository;

import com.green4.travuler.community.feed.entity.FeedComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeedCommentRepositoryCustom {
    // Page<FeedComment> getFeedComments(Long id,Pageable pageable);
     Page<FeedComment> getFeedComments(Long id,Pageable pageable);
}
