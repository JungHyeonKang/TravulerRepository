package com.green4.travuler.community.feed.repository;

import com.green4.travuler.community.feed.entity.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;

import static com.green4.travuler.community.feed.entity.QFeed.feed;
import static com.green4.travuler.community.feed.entity.QFeedComment.feedComment;
import static com.green4.travuler.community.feed.entity.QFeedUser.feedUser;

public class FeedCommentRepositoryImpl implements FeedCommentRepositoryCustom{
    private JPAQueryFactory queryFactory;

    public FeedCommentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }



//    public Page<FeedComment> getFeedComments(Long id,Pageable pageable) {
//        List<FeedComment> feedCommentList = queryFactory.selectFrom(feedComment)
//                .join(feedComment.feed, feed).fetchJoin()
//                .join(feedComment.feedUser, feedUser).fetchJoin()
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .where(feedIdEq(id))
//                .orderBy(
//                        feedComment.createdDate.asc()
//                )
//                .fetch();
//
//        Long totalCount = queryFactory.select(feedComment.count())
//                .from(feedComment)
//                .fetchOne();
//        return new PageImpl<>(feedCommentList,pageable,totalCount);
//    }
//
    @Override
    public Page<FeedComment> getFeedComments(Long id,Pageable pageable) {
        List<FeedComment> feedCommentList = queryFactory.selectFrom(feedComment)
                .join(feedComment.feed, feed).fetchJoin()
                .join(feedComment.feedUser, feedUser).fetchJoin()
                .where(feedIdEq(id))
                .orderBy(
                        feedComment.parent.id.asc().nullsFirst(),feedComment.createdDate.asc()
                )
                .fetch();

        Long totalCount = queryFactory.select(feedComment.count())
                .from(feedComment)
                .fetchOne();
        return new PageImpl<>(feedCommentList,pageable,totalCount);
    }

    private BooleanExpression feedIdEq(Long id){
        return id != null ? feed.id.eq(id) : null;
    }

}
