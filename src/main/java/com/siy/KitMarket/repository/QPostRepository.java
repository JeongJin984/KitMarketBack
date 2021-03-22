package com.siy.KitMarket.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.siy.KitMarket.domain.entity.Application;
import com.siy.KitMarket.domain.entity.QApplication;
import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.domain.entity.account.QAccount;
import com.siy.KitMarket.domain.entity.account.QAccountRole;
import com.siy.KitMarket.domain.entity.post.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class QPostRepository {
    @Autowired
    JPAQueryFactory queryFactory;

    /**
     * Study 전체 조회
     */
    public List<Study> findStudyList() {
        QStudy study = new QStudy("s");

        List<Study> result = queryFactory
                .selectFrom(study)
                .fetch();
        return result;
    }

    /**
     * CarFull 전체 조회
     */
    public List<CarFull> findCarFullList() {
        QCarFull carFull = new QCarFull("cf");

        List<CarFull> result = queryFactory
                .selectFrom(carFull)
                .fetch();

        return result;
    }
    /**
     * Contest 전체조회
     */
    public List<Contest> findContestList() {
        QContest contest = new QContest("ct");

        List<Contest> result = queryFactory
                .selectFrom(contest)
                .fetch();
        return result;
    }
    /**
     * App과 같이 조회
     */
    public Post findPostWithAppById(Long Id){
        QPost post = new QPost("p");
        QApplication application = new QApplication("a");


        return queryFactory
                .selectFrom(post)
                .distinct()
                .join(post.applications, application)
                .fetchJoin()
                .where(post.id.eq(Id))
                .fetchOne();


    }


}
