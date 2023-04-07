package com.green4.travuler.community.tag.repostitory;

import com.green4.travuler.community.feed.entity.Feed;
import com.green4.travuler.community.tag.dto.HashTagSearch;
import com.green4.travuler.community.tag.entity.HashTag;
import com.green4.travuler.community.tag.entity.QFeedHashTag;
import com.green4.travuler.community.tag.entity.QHashTag;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;

import static com.green4.travuler.community.tag.entity.QFeedHashTag.feedHashTag;
import static com.green4.travuler.community.tag.entity.QHashTag.hashTag;
import static org.springframework.util.StringUtils.hasText;

public class HashTagRepositoryImpl implements HashTagRepositoryCustom{
    private JPAQueryFactory queryFactory;

    public HashTagRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public Page<HashTag> getHashTags(Pageable pageable,String content) {
        List<HashTag> hashTagList = queryFactory.select(hashTag).distinct()
                .where(hashtagContentEq(content))
                .from(hashTag)
                .fetch();

        Long totalCount = queryFactory.select(hashTag.count())
                .where(hashtagContentEq(content))
                .from(hashTag)
                .fetchOne();

        return new PageImpl<>(hashTagList,pageable,totalCount);

    }

    BooleanExpression hashtagContentEq(String content){

        return hasText(content) ? hashTag.content.like(content+"%") : null;
    }
}
