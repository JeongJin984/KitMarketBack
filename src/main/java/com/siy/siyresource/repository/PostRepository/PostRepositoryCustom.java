package com.siy.siyresource.repository.PostRepository;


import com.siy.siyresource.domain.condition.PostSearchCondition;
import com.siy.siyresource.domain.dto.post.CarPoolDto;
import com.siy.siyresource.domain.dto.Linear.PostLinearDto;
import com.siy.siyresource.domain.entity.post.CarPool.CarPool;
import com.siy.siyresource.domain.entity.post.Contest.Contest;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.domain.entity.post.Study.Study;
import com.siy.siyresource.domain.dto.post.ContestDto;
import com.siy.siyresource.domain.dto.post.PostDto;
import com.siy.siyresource.domain.dto.post.StudyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    List<Study> findStudyList();
    List<CarPool> findCarPoolList();
    List<Contest> findContestList();
    
    /*
    * 조건 탐색 함수
    * */
    Post findPostWithAppById(PostSearchCondition condition);
    Page<PostLinearDto> findParticipatingPost(PostSearchCondition condition, Pageable pageable);

    /*
     * 기본 엔티티 검색
     **/
    Post findPostById(PostSearchCondition condition);

    /*
    * Paging 함수
    * */
    Page<PostDto> findPostListWithPaging(String status, PageRequest pageable);
    Page<PostDto> findStudyListWithPaging(String status, Pageable pageable);
    Page<PostDto> findCarPoolListWithPaging(String status, Pageable pageable);
    Page<PostDto> findContestListWithPaging(String status, Pageable pageable);

    Page<PostLinearDto> findPostLinearListWithPaging(Pageable pageable);
    Page<PostLinearDto> findPostListByUsername(PostSearchCondition condition, Pageable pageable);
    Page<PostLinearDto> findPostListByApplicationUserName(PostSearchCondition condition, PageRequest pageable);

    Page<PostDto> findSearchList(PostSearchCondition condition, PageRequest page);

}
