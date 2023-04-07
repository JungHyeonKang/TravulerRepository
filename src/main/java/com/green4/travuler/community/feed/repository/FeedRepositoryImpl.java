package com.green4.travuler.community.feed.repository;

import com.green4.travuler.community.feed.dto.FeedSearch;
import com.green4.travuler.community.feed.entity.Feed;
import com.green4.travuler.community.feed.entity.QFeed;
import com.green4.travuler.community.feed.entity.QFeedUser;
import com.green4.travuler.community.tag.entity.FeedHashTag;
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

import static com.green4.travuler.community.feed.entity.QFeed.feed;
import static com.green4.travuler.community.feed.entity.QFeedUser.feedUser;
import static com.green4.travuler.community.tag.entity.QFeedHashTag.feedHashTag;
import static com.green4.travuler.community.tag.entity.QHashTag.hashTag;
import static org.springframework.util.StringUtils.hasText;


public class FeedRepositoryImpl implements FeedRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public FeedRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Feed> findAllFeeds(Pageable pageable,FeedSearch feedSearch) {

        List<Feed> feedList = queryFactory
                .selectFrom(feed)
                .join(feed.feedUser, feedUser).fetchJoin()
                .where(contentEq(feedSearch.getContent()))
                .orderBy(feed.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory
                .select(feed.count())
                .from(feed)
                .fetchOne();
        return new PageImpl<>(feedList,pageable,totalCount);
    }


    private BooleanExpression contentEq(String content) {

        return hasText(content) ? compareContent(content) : null;
    }
    private BooleanExpression compareContent(String content) {
        if(content.startsWith("#")){

            Long hashTagId = queryFactory.select(hashTag.id).from(hashTag).where(hashTag.content.eq(content)).fetchOne();

            List<Long> feedList = queryFactory.select(feedHashTag.feed.id).from(feedHashTag).where(feedHashTag.hashTag.id.eq(hashTagId)).fetch();

            return hasText(content) ?  feed.id.in(feedList) : null;

        }
        else{
            return hasText(content) ?  feed.content.contains(content) : null;
        }
    }
}
