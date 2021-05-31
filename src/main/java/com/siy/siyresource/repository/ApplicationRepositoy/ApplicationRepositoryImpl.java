package com.siy.siyresource.repository.ApplicationRepositoy;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.siy.siyresource.domain.entity.Application;
import com.siy.siyresource.domain.entity.QApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import static com.siy.siyresource.domain.entity.QApplication.application;
import static com.siy.siyresource.domain.entity.post.QPost.post;

public class ApplicationRepositoryImpl implements ApplicationRepositoryCustom{
    private JPAQueryFactory queryFactory;


    @Autowired
    public ApplicationRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Application findByUserName(String username, Long postId) {
        Application application = queryFactory
                .selectFrom(QApplication.application)
                .where(usernameEq(username), postEq(postId))
                .fetchFirst();

        return application;
    }

    @Override
    public Application findByAppId(Long Id) {
        return queryFactory.selectFrom(application)
                .where(idEq(Id))
                .fetchOne();
    }

    private BooleanExpression idEq(Long id) {
        return id != null ? application.id.eq(id) : null;
    }


    private BooleanExpression usernameEq(String username) {
        return StringUtils.hasText(username) ? application.username.eq(username):null;
    }
    private BooleanExpression postEq(Long id) {
        return id != null ? application.post().id.eq(id) : null;
    }

}
