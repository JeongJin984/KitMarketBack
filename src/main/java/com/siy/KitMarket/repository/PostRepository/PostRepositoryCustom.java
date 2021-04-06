package com.siy.KitMarket.repository.PostRepository;


import com.siy.KitMarket.domain.dto.post.CarFullDto;
import com.siy.KitMarket.domain.dto.post.ContestDto;
import com.siy.KitMarket.domain.dto.post.PostDto;
import com.siy.KitMarket.domain.dto.post.StudyDto;
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
    Post findPostWithAppById(Long Id);
    Page<PostDto> findParticipatingPost(String username, Pageable pageable);
    Post findPostById(Long Id);
    
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
    
    

}
