package com.green4.travuler.community.feed.repository;

import com.green4.travuler.community.feed.entity.Feed;
import com.green4.travuler.community.feed.entity.FeedComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedCommentRepository extends JpaRepository<FeedComment,Long>,FeedCommentRepositoryCustom {
    FeedComment findFeedCommentById(Long id);
    List<FeedComment> findFeedCommentsByParentId(Long id);
}
