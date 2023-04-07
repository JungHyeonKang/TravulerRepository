package com.green4.travuler.community.tag.repostitory;

import com.green4.travuler.community.feed.entity.Feed;
import com.green4.travuler.community.tag.dto.HashTagSearch;
import com.green4.travuler.community.tag.entity.HashTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HashTagRepositoryCustom {
    Page<HashTag> getHashTags(Pageable pageable,String content);

}
