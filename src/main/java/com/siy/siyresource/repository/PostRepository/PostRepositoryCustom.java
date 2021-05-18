package com.siy.siyresource.repository.PostRepository;


import com.siy.siyresource.domain.condition.PostSearchCondition;
import com.siy.siyresource.domain.dto.post.CarPoolDto;
import com.siy.siyresource.domain.dto.post.Linear.PostLinearDto;
import com.siy.siyresource.domain.entity.post.CarPool;
import com.siy.siyresource.domain.entity.post.Contest.Contest;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.domain.entity.post.Study;
import com.siy.siyresource.domain.dto.post.ContestDto;
import com.siy.siyresource.domain.dto.post.PostDto;
import com.siy.siyresource.domain.dto.post.StudyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    List<Study> findStudyList();
    List<CarPool> findCarFullList();
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
    Contest findContestById(PostSearchCondition condition);
    /*
    * WithApplication 함수
    * */
    //void JoinPost(Post post, Application application);


    /*
    * Paging 함수
    * */
    Page<PostDto> findPostListWithPaging(Pageable pageable);
    Page<StudyDto> findStudyListWithPaging(Pageable pageable);
    Page<CarPoolDto> findCarFullListWithPaging(Pageable pageable);
    Page<ContestDto> findContestListWithPaging(Pageable pageable);
    Page<PostLinearDto> findPostLinearListWithPaging(Pageable pageable);
    Page<PostLinearDto> findPostListByUsername(PostSearchCondition condition, Pageable pageable);
    Page<PostLinearDto> findPostListByApplicationUserName(PostSearchCondition condition, PageRequest pageable);


}
