package com.siy.siyresource.repository.PostRepository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.siy.siyresource.domain.condition.PostSearchCondition;
import com.siy.siyresource.domain.dto.post.*;
import com.siy.siyresource.domain.dto.Linear.PostLinearDto;
import com.siy.siyresource.domain.dto.Linear.QPostLinearDto;
import com.siy.siyresource.domain.entity.QApplication;
import com.siy.siyresource.domain.entity.post.*;
import com.siy.siyresource.domain.entity.post.CarPool.CarPool;
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
import static com.siy.siyresource.domain.entity.post.CarPool.QCarPool.carPool;
import static com.siy.siyresource.domain.entity.post.Contest.QContest.contest;
import static com.siy.siyresource.domain.entity.post.QPost.post;
import static com.siy.siyresource.domain.entity.post.Study.QStudy.*;
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
        System.out.println("condition.getParticipantName() = " + condition.getParticipantName());
        List<PostLinearDto> content = queryFactory
                .select(new QPostLinearDto(
                        post.id,
                        post.category,
                        post.title,
                        post.writer,
                        post.createdAt
                ))
                .from(post)
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
                .where(PartipantUsernameEq(username));
        return result;
    }



    @Override
    public Page<PostDto> findSearchList(PostSearchCondition condition, PageRequest page) {
        System.out.println("condition = " + condition);

        List<PostDto> content = queryFactory
                .select(new QPostDto(
                        post.id,
                        post.writer,
                        post.title,
                        post.content,
                        post.dueDate,
                        post.createdAt,
                        post.maxNumber,
                        post.currentNumber,
                        post.category,
                        post.postStatus.stringValue()
                ))
                .from(post)
                .where(searchContains(condition).and(selectedStatus(condition.getStatus())))
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
                .where(searchContains(condition), selectedStatus(condition.getStatus()));
    }


    /**
     *  모집중인 게시물 보기
     */
    @Override
    public Page<PostDto> findPostListWithPaging(String status, PageRequest pageable) {
        List<PostDto> content = queryFactory
                .select(new QPostDto(
                        post.id,
                        post.writer,
                        post.title,
                        post.content,
                        post.dueDate,
                        post.createdAt,
                        post.maxNumber,
                        post.currentNumber,
                        post.category,
                        post.postStatus.stringValue()
                ))
                .from(post)
                .where(selectedStatus(status))
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Post> countQuery = countPostingListQuery(status);
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private JPAQuery<Post> countPostingListQuery(String status) {
        return queryFactory
                .selectFrom(post)
                .where(selectedStatus(status));
    }


    /**
     * Study Paging List
     */
    @Override
    public Page<PostDto> findStudyListWithPaging(String status, Pageable pageable) {
        List<PostDto> content = queryFactory
                .select(new QPostDto(
                        study.id,
                        study.writer,
                        study.title,
                        study.content,
                        study.dueDate,
                        study.createdAt,
                        study.maxNumber,
                        study.currentNumber,
                        study.category,
                        study.postStatus.stringValue()
                ))
                .from(study)
                .where(selectedStatus(status))
                .orderBy(study.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Study> countQuery = countStudyQuery(status);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }


    /**
     * CarPool Paging List
     *
     * @return
     */
    @Override
    public Page<PostDto> findCarPoolListWithPaging(String status, Pageable pageable) {
        List<PostDto> content = queryFactory
                .select(new QPostDto(
                        carPool.id,
                        carPool.writer,
                        carPool.title,
                        carPool.content,
                        carPool.dueDate,
                        carPool.createdAt,
                        carPool.maxNumber,
                        carPool.currentNumber,
                        carPool.category,
                        carPool.postStatus.stringValue()
                ))
                .from(carPool)
                .where(selectedStatus(status))
                .orderBy(carPool.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<CarPool> countQuery = countCarPoolQuery(status);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }


    /**
     * Contest Paging List
     */
    @Override
    public Page<PostDto> findContestListWithPaging(String status, Pageable pageable) {
        List<PostDto> content = queryFactory
                .select(new QPostDto(
                        contest.id,
                        contest.writer,
                        contest.title,
                        contest.content,
                        contest.dueDate,
                        contest.createdAt,
                        contest.maxNumber,
                        contest.currentNumber,
                        contest.category,
                        contest.postStatus.stringValue()
                ))
                .from(contest)
                .where(selectedStatus(status))
                .orderBy(contest.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Contest> countQuery = countContestQuery(status);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private JPAQuery<Contest> countContestQuery(String status) {
        JPAQuery<Contest> countQuery = queryFactory
                .selectFrom(contest)
                .where(selectedStatus(status));
        return countQuery;
    }

    private JPAQuery<Post> countPostQuery() {
        return queryFactory
                .selectFrom(post);
    }

    private JPAQuery<CarPool> countCarPoolQuery(String status) {
        JPAQuery<CarPool> countQuery = queryFactory
                .selectFrom(carPool)
                .where(selectedStatus(status));
        return countQuery;
    }

    private JPAQuery<Study> countStudyQuery(String status) {
        JPAQuery<Study> countQuery = queryFactory
                .selectFrom(study)
                .where(selectedStatus(status));

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
        return hasText(username) ? post.participants.contains(username):null;
    }

    private BooleanExpression selectedStatus(String status) {
        if(status.equals("POSTING"))
            return post.postStatus.eq(PostStatus.POSTING);
        else if(status.equals("CLOSED"))
            return post.postStatus.eq(PostStatus.CLOSE);
        else if(status.equals("OPERATING"))
            return post.postStatus.eq(PostStatus.OPERATING);
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




}