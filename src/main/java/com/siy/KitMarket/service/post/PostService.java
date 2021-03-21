package com.siy.KitMarket.service.post;

import com.siy.KitMarket.domain.entity.post.CarFull;
import com.siy.KitMarket.domain.entity.post.Contest;
import com.siy.KitMarket.domain.entity.post.Post;
import com.siy.KitMarket.domain.entity.post.Study;
import com.siy.KitMarket.repository.PostRepository;
import com.siy.KitMarket.repository.QPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
     */
    public List<Post> findPostList(){
        List<Post> results = postRepository.findAll();
        return results;
    }

    /**
     * Study 전체 조회
     */
    public List<Study> findStudyList(){
        return qPostRepository.findStudyList();
    }
    /**
     * CarFull 전체 조회
     */
    public List<CarFull> findCarFulList(){
        return qPostRepository.findCarFullList();
    }

    /**
     * Contest 전체 조회
     */
    public List<Contest> findContestList(){
        return qPostRepository.findContestList();
    }


    /**
     * 스터디 하나 조회
     * */
    public Study findStudyOne(Long postId){
        return (Study)postRepository.findById(postId).get();
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
