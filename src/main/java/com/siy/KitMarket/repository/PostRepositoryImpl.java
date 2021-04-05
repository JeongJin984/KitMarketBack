package com.siy.KitMarket.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.siy.KitMarket.domain.dto.post.*;
import com.siy.KitMarket.domain.entity.QApplication;
import com.siy.KitMarket.domain.entity.post.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.siy.KitMarket.domain.entity.post.QCarFull.carFull;
import static com.siy.KitMarket.domain.entity.post.QContest.contest;
import static com.siy.KitMarket.domain.entity.post.QPost.post;
import static com.siy.KitMarket.domain.entity.post.QStudy.study1;

@Repository
public class PostRepositoryImpl implements PostRepositoryCustom{
    @Autowired
    JPAQueryFactory queryFactory;

    @Override
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
    @Override
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
    @Override
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
    @Override
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

    /**
     * Post Paging List
     */
    @Override
    public Page<PostDto> findPostListWithPaging(Pageable pageable) {
        List<PostDto> content = queryFactory
                .select(new QPostDto(
                        post.id.as("id"),
                        post.account().username,
                        post.title,
                        post.content
                ))
                .from(post)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Post> countQuery = queryFactory
                .selectFrom(post);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    /**
     * Study Paging List
     */
    @Override
    public Page<StudyDto> findStudyListWithPaging(Pageable pageable) {
        List<StudyDto> content = queryFactory
                .select(new QStudyDto(
                        study1.id.as("id"),
                        study1.account().username,
                        study1.title,
                        study1.content
                ))
                .from(study1)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Study> countQuery = queryFactory
                .selectFrom(study1);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    /**
     * CarFull Paging List
     * @return
     */
    @Override
    public Page<CarFullDto> findCarFullListWithPaging(Pageable pageable) {
        List<CarFullDto> content = queryFactory
                .select(new QCarFullDto(
                        carFull.id,
                        carFull.account().username,
                        carFull.title,
                        carFull.content
                ))
                .from(carFull)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<CarFull> countQuery = queryFactory
                .selectFrom(carFull);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    /**
     * Contest Paging List
     */
    @Override
    public Page<ContestDto> findContestListWithPaging(Pageable pageable) {
        List<ContestDto> content = queryFactory
                .select(new QContestDto(
                        contest.id,
                        contest.account().username,
                        contest.title,
                        contest.content
                ))
                .from(contest)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Contest> countQuery = queryFactory
                .selectFrom(contest);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }



//
//    private BooleanExpression usernameEq(String username) {
//        return StringUtils.hasText(username) ? post.username.eq(username) : null;
//    }
//    private BooleanExpression teamNameEq(String teamName) {
//        return StringUtils.hasText(teamName) ? team.name.eq(teamName) : null;
//    }
//    private BooleanExpression ageGoe(Integer ageGoe) {
//        return ageGoe != null ? member.age.goe(ageGoe) : null;
//    }
//    private BooleanExpression ageLoe(Integer ageLoe) {
//        return ageLoe != null ? member.age.loe(ageLoe) : null;
//    }

}
