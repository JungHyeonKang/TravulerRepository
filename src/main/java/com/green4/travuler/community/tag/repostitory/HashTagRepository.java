package com.green4.travuler.community.tag.repostitory;


import com.green4.travuler.community.feed.entity.FeedUser;
import com.green4.travuler.community.tag.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HashTagRepository extends JpaRepository<HashTag,Long>,HashTagRepositoryCustom {
    public Optional<HashTag> findByContent(String content);
}
