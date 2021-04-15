package com.siy.KitMarket.repository.PostRepository;


import com.siy.KitMarket.domain.condition.PostSearchCondition;
import com.siy.KitMarket.domain.dto.post.*;
import com.siy.KitMarket.domain.dto.post.Linear.PostLinearDto;
import com.siy.KitMarket.domain.entity.post.CarFull;
import com.siy.KitMarket.domain.entity.post.Contest;
import com.siy.KitMarket.domain.entity.post.Post;
import com.siy.KitMarket.domain.entity.post.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    List<Study> findStudyList();
    List<CarFull> findCarFullList();
    List<Contest> findContestList();
    
    /*
    * 조건 탐색 함수
    * */
    Post findPostWithAppById(PostSearchCondition condition);
    Page<PostLinearDto> findParticipatingPost(PostSearchCondition condition, Pageable pageable);
    Post findPostById(PostSearchCondition condition);
    
    /*
    * WithApplication 함수
    * */

    /*
    * Paging 함수
    * */
    Page<PostDto> findPostListWithPaging(Pageable pageable);
    Page<StudyDto> findStudyListWithPaging(Pageable pageable);
    Page<CarFullDto> findCarFullListWithPaging(Pageable pageable);
    Page<ContestDto> findContestListWithPaging(Pageable pageable);
    Page<PostLinearDto> findPostLinearListWithPaging(Pageable pageable);


}
