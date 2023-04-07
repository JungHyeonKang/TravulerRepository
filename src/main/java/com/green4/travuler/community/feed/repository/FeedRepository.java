package com.green4.travuler.community.feed.repository;

import com.green4.travuler.community.feed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed,Long> , FeedRepositoryCustom {

}
