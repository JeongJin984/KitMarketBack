package com.siy.KitMarket.repository.PostRepository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.siy.KitMarket.domain.dto.post.*;
import com.siy.KitMarket.domain.dto.post.Linear.PostLinearDto;
import com.siy.KitMarket.domain.entity.QApplication;
import com.siy.KitMarket.domain.entity.account.QAccount;
import com.siy.KitMarket.domain.entity.accountPost.AccountPost;
import com.siy.KitMarket.domain.entity.accountPost.QAccountPost;
import com.siy.KitMarket.domain.entity.post.*;
import com.siy.KitMarket.repository.AccountRepository.AccountRepository;
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
import static org.springframework.util.StringUtils.hasText;

@Repository
public class PostRepositoryImpl implements PostRepositoryCustom{

    JPAQueryFactory queryFactory;
    AccountRepository accountRepository;

    @Autowired
    public PostRepositoryImpl(JPAQueryFactory queryFactory, AccountRepository accountRepository) {
        this.queryFactory = queryFactory;
        this.accountRepository = accountRepository;
    }

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
                .join(post.applications, application).fetchJoin()
                .where(post.id.eq(Id))
                .fetchOne();
    }

    @Override
    public Page<PostLinearDto> findParticipatingPost(String username, Pageable pageable) {
        QAccountPost accountPost = new QAccountPost("accountPost");
        QAccount account = new QAccount("account");

        List<PostLinearDto> content = queryFactory
                .select(new QPostLinearDto(
                        post.id,
                        post.category,
                        post.title,
                        post.writer,
                        post.createdAt
                ))
                .from(post)
                .join(post.accountPosts, accountPost)
                .where(accountPost.username.eq(username))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Post> countQuery = queryFactory
                .select(post)
                .from(post)
                .join(post.accountPosts, accountPost).fetchJoin()
                .where(accountPost.username.eq(username));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }


    @Override
    public Post findPostById(Long Id){
        QPost post = new QPost("p");
        QApplication application = new QApplication("a");

        return queryFactory
                .selectFrom(post)
                .distinct()
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
                        post.writer,
                        post.title,
                        post.content,
                        post.createdAt,
                        post.maxNumber,
                        post.currentNumber,
                        post.deadLine,
                        post.category
                ))
                .from(post)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Post> countQuery = countQuery();

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<PostLinearDto> findPostLinearListWithPaging(Pageable pageable) {
        List<PostLinearDto> content = queryFactory
                .select(new QPostLinearDto(
                        post.id,
                        post.category,
                        post.title,
                        post.writer,
                        post.createdAt
                ))
                .from(post)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Post> countQuery = countQuery();

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private JPAQuery<Post> countQuery() {
        return queryFactory
                .selectFrom(post);
    }

    /**
     * Study Paging List
     */
    @Override
    public Page<StudyDto> findStudyListWithPaging(Pageable pageable) {
        List<StudyDto> content = queryFactory
                .select(new QStudyDto(
                        study1.id.as("id"),
                        study1.writer,
                        study1.title,
                        study1.content,
                        study1.createdAt,
                        study1.maxNumber,
                        study1.currentNumber,
                        study1.deadLine,
                        study1.category
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
                        carFull.id.as("id"),
                        carFull.writer,
                        carFull.title,
                        carFull.content,
                        carFull.createdAt,
                        carFull.maxNumber,
                        carFull.currentNumber,
                        carFull.deadLine,
                        carFull.category
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
                        contest.id.as("id"),
                        contest.writer,
                        contest.title,
                        contest.content,
                        contest.createdAt,
                        contest.maxNumber,
                        contest.currentNumber,
                        contest.deadLine,
                        contest.category
                ))
                .from(contest)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Contest> countQuery = queryFactory
                .selectFrom(contest);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }



}
