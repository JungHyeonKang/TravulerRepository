package com.green4.travuler.community.feed.repository;

import com.green4.travuler.community.feed.dto.FeedListDto;
import com.green4.travuler.community.feed.dto.FeedSearch;
import com.green4.travuler.community.feed.entity.Feed;
import com.green4.travuler.community.tag.dto.HashTagSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeedRepositoryCustom {
    Page<Feed> findAllFeeds(Pageable pageable,FeedSearch feedSearch);


}
