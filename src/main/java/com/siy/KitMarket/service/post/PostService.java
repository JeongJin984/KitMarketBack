package com.siy.KitMarket.service.post;

import com.siy.KitMarket.domain.dto.post.CarFullDto;
import com.siy.KitMarket.domain.dto.post.ContestDto;
import com.siy.KitMarket.domain.dto.post.PostDto;
import com.siy.KitMarket.domain.dto.post.StudyDto;
import com.siy.KitMarket.domain.entity.post.CarFull;
import com.siy.KitMarket.domain.entity.post.Contest;
import com.siy.KitMarket.domain.entity.post.Post;
import com.siy.KitMarket.domain.entity.post.Study;
import com.siy.KitMarket.repository.PostRepository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    /*
    * 포스트 저장
    * */
    public Long save(Post post) {
        return postRepository.save(post).getId();
    }

    /**
     * 포스트 전체 조회
     * @return
     */
    public Page<PostDto> findPostList(int offset, int size){
        PageRequest page = PageRequest.of(offset, size);

        Page<PostDto> results = postRepository.findPostListWithPaging(page);

        return results;
    }

    /**
     * Study 전체 조회
     * @return
     */
    public Page<StudyDto> findStudyList(int offset, int size){
        PageRequest page = PageRequest.of(offset, size);

        Page<StudyDto> results =postRepository.findStudyListWithPaging(page);
        return results;
    }
    /**
     * CarFull 전체 조회
     * @return
     */
    public Page<CarFullDto> findCarFulList(int offset, int size){
        PageRequest page = PageRequest.of(offset, size);

        Page<CarFullDto> results = postRepository.findCarFullListWithPaging(page);
        return results;
    }

    /**
     * Contest 전체 조회
     * @return
     */
    public Page<ContestDto> findContestList(int offset, int size){
        PageRequest page = PageRequest.of(offset, size);

        Page<ContestDto> results = postRepository.findContestListWithPaging(page);

        return results;
    }
}
