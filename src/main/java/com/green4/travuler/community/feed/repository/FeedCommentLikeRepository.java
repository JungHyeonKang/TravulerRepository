package com.green4.travuler.community.feed.repository;

import com.green4.travuler.community.feed.entity.FeedCommentLike;
import com.green4.travuler.community.feed.entity.FeedLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedCommentLikeRepository extends JpaRepository<FeedCommentLike,Long> {
    public Optional<FeedCommentLike> findByFeedUserIdAndFeedCommentId(Long feedUser , Long id);
    public List<FeedCommentLike> findByFeedCommentId(Long id);
}
