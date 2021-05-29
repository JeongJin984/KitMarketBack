package com.siy.siyresource.repository.PostRepository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.siy.siyresource.domain.condition.PostSearchCondition;
import com.siy.siyresource.domain.dto.post.*;
import com.siy.siyresource.domain.dto.post.Linear.PostLinearDto;
import com.siy.siyresource.domain.dto.post.Linear.QPostLinearDto;
import com.siy.siyresource.domain.entity.QApplication;
import com.siy.siyresource.domain.entity.post.*;
import com.siy.siyresource.domain.entity.post.Contest.Contest;
import com.siy.siyresource.domain.entity.post.Study.Study;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.siy.siyresource.domain.entity.QApplication.application;
import static com.siy.siyresource.domain.entity.QParticipant.participant;
import static com.siy.siyresource.domain.entity.post.QCarPool.carPool;
import static com.siy.siyresource.domain.entity.post.QStudy.study;
import static com.siy.siyresource.domain.entity.post.Contest.QContest.contest;
import static com.siy.siyresource.domain.entity.post.QPost.post;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class PostRepositoryImpl implements PostRepositoryCustom {

    JPAQueryFactory queryFactory;

    @Autowired
    public PostRepositoryImpl(JPAQueryFactory queryFactory  ) {
        this.queryFactory = queryFactory;
    }

    /**
    /**
     * 단순 study 전체 조회
     */
    @Override
    public List<Study> findStudyList() {
        List<Study> result = queryFactory
                .selectFrom(study)
                .fetch();

        return result;
    }

    /**
     * 단순 CarPool 전체 조회
     */
    @Override
    public List<CarPool> findCarPoolList() {
        List<CarPool> result = queryFactory
                .selectFrom(carPool)
                .fetch();

        return result;
    }

    /**
     * 단순 Contest 전체 조회
     */
    @Override
    public List<Contest> findContestList() {
        List<Contest> result = queryFactory
                .selectFrom(contest)
                .fetch();
        return result;
    }

    /**
     * App과 같이 Post 전체 조회
     */
    @Override
    public Post findPostWithAppById(PostSearchCondition condition) {
        QApplication application = new QApplication("a");

        Post post = queryFactory
                .selectFrom(QPost.post)
                .distinct()
                .join(QPost.post.applications, application).fetchJoin()
                .where(postIdEqual(condition.getId()))
                .fetchOne();

        return post;
    }







    /**
     * 단순 post 1개 검색
     */
    @Override
    public Post findPostById(PostSearchCondition condition) {
        Long id = condition.getId();
        return queryFactory
                .selectFrom(post)
                .where(postIdEqual(id))
                .fetchOne();
    }
    /**
     * 단순 Contest 1개 검색
     */
    @Override
    public Contest findContestById(PostSearchCondition condition) {
        Long id = condition.getId();
        return queryFactory
                .selectFrom(contest)
                .where(postIdEqual(id))
                .fetchOne();
    }

    /**
     * Paging post 전체 조회
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
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        System.out.println("content = " + content);

        JPAQuery<Post> countQuery = countPostQuery();

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    /**
     * Paging, Linear 방식 Post 전체 조회
     */
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

        JPAQuery<Post> countQuery = countPostQuery();

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<PostLinearDto> findPostListByUsername(PostSearchCondition condition, Pageable pageable) {
        List<PostLinearDto> content = queryFactory
                .select(new QPostLinearDto(
                        post.id,
                        post.category,
                        post.title,
                        post.writer,
                        post.createdAt
                ))
                .from(post)
                .where(postWriterEqual(condition.getUsername()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Post> countQuery = countPostWriterQuery(condition.getUsername());
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);

    }

    @Override
    public Page<PostLinearDto> findPostListByApplicationUserName(PostSearchCondition condition, PageRequest pageable) {

        List<PostLinearDto> content = queryFactory
                .select(new QPostLinearDto(
                        post.id,
                        post.category,
                        post.title,
                        post.writer,
                        post.createdAt
                ))
                .distinct()
                .from(post,application)
                .join(post.applications, application)
                .where(
                        applicationPostUserNameEq(condition.getUsername())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Post> countQuery = countPostByApplicationUserNameQuery(condition.getUsername());
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private JPAQuery<Post> countPostByApplicationUserNameQuery(String username) {
        JPAQuery<Post> result = queryFactory
                .selectFrom(post)
                .join(post.applications, application)
                .where(applicationPostUserNameEq(username));
        return result;
    }



    /**
     * 내가 참여하고 있는 post 전체 검색
     */
    @Override
    public Page<PostLinearDto> findParticipatingPost(PostSearchCondition condition, Pageable pageable) {
        List<PostLinearDto> content = queryFactory
                .select(new QPostLinearDto(
                        post.id,
                        post.category,
                        post.title,
                        post.writer,
                        post.createdAt
                ))
                .distinct()
                .from(post, participant)
                .join(post.participants, participant)
                .where(
                        PartipantUsernameEq(condition.getParticipantName())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Post> countQuery = countPostJoinParticipantQuery(condition.getParticipantName());

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private JPAQuery<Post> countPostJoinParticipantQuery(String username) {
        JPAQuery<Post> result = queryFactory
                .selectFrom(post)
                .join(post.participants, participant)
                .where(PartipantUsernameEq(username));
        return result;
    }

    /**
     *  모집중인 포스팅 보
     */
    @Override
    public Page<PostDto> findPostingList(PageRequest pageable) {
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
                .where(Posting())
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Post> countQuery = countPostingListQuery();
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private JPAQuery<Post> countPostingListQuery() {
        return queryFactory
                .selectFrom(post)
                .where(Posting());
    }


    /**
     * 마감된 포스팅 보기
     * */
    @Override
    public Page<PostDto> findClosedList(PageRequest pageable) {
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
                .where(Closed())
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Post> countQuery = countClosedListQuery();
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }


    private JPAQuery<Post> countClosedListQuery() {
        return queryFactory
                .selectFrom(post)
                .where(Closed());
    }



    @Override
    public Page<PostDto> findSearchList(PostSearchCondition condition, PageRequest page) {
        System.out.println("condition = " + condition);

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
                .where(searchContains(condition).and(selectedStatus(condition)))
                .orderBy(post.createdAt.desc())
                .offset(page.getOffset())
                .limit(page.getPageSize())
                .fetch();
        JPAQuery<Post> countQuery = countSearchListQuery(condition);
        return PageableExecutionUtils.getPage(content, page, countQuery::fetchCount);
    }

    private JPAQuery<Post> countSearchListQuery(PostSearchCondition condition) {
        return queryFactory
                .selectFrom(post)
                .where(searchContains(condition), selectedStatus(condition));
    }

    private BooleanExpression selectedStatus(PostSearchCondition condition) {
        if(condition.getStatus().equals("POSTING")){
            System.out.println("posting");
            return Posting();
        }
        else if (condition.getStatus().equals("CLOSED")){
            System.out.println("closed");
            return Closed();
        }
        else
            return null;
    }

    private BooleanExpression searchContains(PostSearchCondition condition) {
        BooleanExpression title = hasText(condition.getTitle()) ? post.title.contains(condition.getTitle()) : null;
        BooleanExpression username = hasText(condition.getUsername()) ? post.writer.contains(condition.getUsername()) : null;

        System.out.println("title = " + title);
        System.out.println("username = " + username);

        if(title != null){
            return title;
        }
        else if (username != null){
            return username;
        }
        else
            return null;
    }


    /**
     * Study Paging List
     */
    @Override
    public Page<StudyDto> findStudyListWithPaging(Pageable pageable) {
        List<StudyDto> content = queryFactory
                .select(new QStudyDto(
                        study.id.as("id"),
                        study.writer,
                        study.title,
                        study.content,
                        study.createdAt,
                        study.maxNumber,
                        study.currentNumber,
                        study.deadLine,
                        study.category
                ))
                .from(study)
                .orderBy(study.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Study> countQuery = countStudyQuery();

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }


    /**
     * CarPool Paging List
     *
     * @return
     */
    @Override
    public Page<CarPoolDto> findCarPoolListWithPaging(Pageable pageable) {
        List<CarPoolDto> content = queryFactory
                .select(new QCarPoolDto(
                        carPool.id.as("id"),
                        carPool.writer,
                        carPool.title,
                        carPool.content,
                        carPool.createdAt,
                        carPool.maxNumber,
                        carPool.currentNumber,
                        carPool.deadLine,
                        carPool.category
                ))
                .from(carPool)
                .orderBy(carPool.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<CarPool> countQuery = countCarPoolQuery();

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
                .orderBy(contest.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Contest> countQuery = countContestQuery();

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private JPAQuery<Contest> countContestQuery() {
        JPAQuery<Contest> countQuery = queryFactory
                .selectFrom(contest);
        return countQuery;
    }

    private JPAQuery<Post> countPostQuery() {
        return queryFactory
                .selectFrom(post);
    }

    private JPAQuery<CarPool> countCarPoolQuery() {
        JPAQuery<CarPool> countQuery = queryFactory
                .selectFrom(carPool);
        return countQuery;
    }

    private JPAQuery<Study> countStudyQuery() {
        JPAQuery<Study> countQuery = queryFactory
                .selectFrom(study);
        return countQuery;
    }


    private JPAQuery<Post> countPostWriterQuery(String username) {
        JPAQuery<Post> countQuery = queryFactory
                .selectFrom(post)
                .where(postWriterEqual(username));

        return countQuery;

    }



    private BooleanExpression postIdEqual(Long id) {
        return id != null ? post.id.eq(id) : null;
    }

    private BooleanExpression postWriterEqual(String username) {
        return hasText(username) ? post.writer.eq(username) :null;
    }

    private BooleanExpression applicationPostUserNameEq(String username) {
        return hasText(username) ? application.username.eq(username):null;
    }

    private BooleanExpression PartipantUsernameEq(String username) {
        return hasText(username) ? participant.username.eq(username):null;
    }

    private BooleanExpression Operating() {
        return post.postStatus.eq(PostStatus.OPERATING);
    }
    private BooleanExpression Posting() {
        return post.postStatus.eq(PostStatus.POSTING);
    }
    private BooleanExpression Closed() {
        return post.postStatus.eq(PostStatus.CLOSE);

    }



}