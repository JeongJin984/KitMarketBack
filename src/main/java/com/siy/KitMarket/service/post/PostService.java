package com.siy.KitMarket.service.post;

import com.siy.KitMarket.domain.dto.post.CarFullDto;
import com.siy.KitMarket.domain.dto.post.PostDto;
import com.siy.KitMarket.domain.dto.post.StudyDto;
import com.siy.KitMarket.domain.entity.post.CarFull;
import com.siy.KitMarket.domain.entity.post.Contest;
import com.siy.KitMarket.domain.entity.post.Post;
import com.siy.KitMarket.domain.entity.post.Study;
import com.siy.KitMarket.repository.PostRepository;
import com.siy.KitMarket.repository.QPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final QPostRepository qPostRepository;
    /**
     *  포스트 저장
     */
    @Transactional
    public void save(Post post){
        postRepository.save(post);
    }

    /**
     * 포스트 전체 조회
     * @return
     */
    public Page<PostDto> findPostList(int offset, int size){
        PageRequest page = PageRequest.of(offset, size);

        Page<PostDto> results = qPostRepository.findPostListWithPaging(page);

        return results;
    }

    /**
     * Study 전체 조회
     * @return
     */
    public Page<StudyDto> findStudyList(int offset, int size){
        PageRequest page = PageRequest.of(offset, size);

        Page<StudyDto> results = qPostRepository.findStudyListWithPaging(page);
        return results;
    }
    /**
     * CarFull 전체 조회
     * @return
     */
    public Page<CarFullDto> findCarFulList(int offset, int size){
        PageRequest page = PageRequest.of(offset, size);

        Page<CarFullDto> results = qPostRepository.findCarFullListWithPaging(page);
        return results;
    }

    /**
     * Contest 전체 조회
     * @return
     */
    public Page<Contest> findContestList(int offset, int size){
        PageRequest page = PageRequest.of(offset, size);

        Page<Contest> results = qPostRepository.findContestListWithPaging(page);

        return results;
    }


    /**
     * 스터디 하나 조회 with app
     * */
    public StudyDto findStudyOne(Long postId){
        Study findStudy;
        Post findPost = qPostRepository.findPostWithAppById(postId);

        if(findPost instanceof Study) {
            findStudy = (Study)findPost;
            return new StudyDto(findPost.getId(),findPost.getTitle(),findPost.getContent(), findPost.getApplications());
        }
        else
            return null;
    }

    /**
     * 공모전 하나 조회
     * */
    public Contest findContestOne(Long postId){
        return (Contest)postRepository.findById(postId).get();
    }
    /**
     * 카풀 하나 조회
     * */
    public CarFull findCarFullOne(Long postId){
        return (CarFull)postRepository.findById(postId).get();
    }




}
