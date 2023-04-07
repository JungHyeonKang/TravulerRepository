package com.green4.travuler.community.feed.repository;

import com.green4.travuler.community.feed.entity.Feed;
import com.green4.travuler.community.feed.entity.FeedLike;
import com.green4.travuler.community.feed.entity.FeedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedLikesRepository extends JpaRepository<FeedLike,Long> {

    public Optional<FeedLike> findByFeedUserIdAndFeedId(Long feedUser , Long feed);
    public List<FeedLike> findByFeedId(Long id);

}
