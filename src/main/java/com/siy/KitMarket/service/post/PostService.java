package com.siy.KitMarket.service.post;

import com.siy.KitMarket.domain.dto.post.CarFullDto;
import com.siy.KitMarket.domain.dto.post.ContestDto;
import com.siy.KitMarket.domain.dto.post.PostDto;
import com.siy.KitMarket.domain.dto.post.StudyDto;
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
import java.util.stream.Collectors;

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
    public List<PostDto> findPostList(){
        List<Post> results = postRepository.findAll();
        List<PostDto> collect = results.stream()
                .map(p -> new PostDto(p.getTitle(), p.getContent()))
                .collect(Collectors.toList());
        return collect;
    }

    /**
     * Study 전체 조회
     */
    public List<StudyDto> findStudyList(){
        List<Study> studyList = qPostRepository.findStudyList();

        List<StudyDto> collect = studyList.stream()
                .map(s -> new StudyDto(s.getTitle(), s.getContent()))
                .collect(Collectors.toList());
        return collect;
    }
    /**
     * CarFull 전체 조회
     */
    public List<CarFullDto> findCarFulList(){
        List<CarFull> carFullList = qPostRepository.findCarFullList();
        List<CarFullDto> collect = carFullList.stream()
                .map(cf -> new CarFullDto(cf.getTitle(), cf.getContent()))
                .collect(Collectors.toList());
        return collect;
    }

    /**
     * Contest 전체 조회
     */
    public List<ContestDto> findContestList(){
        List<Contest> contestList = qPostRepository.findContestList();

        List<ContestDto> collect = contestList.stream()
                .map(ct -> new ContestDto(ct.getTitle(), ct.getContent()))
                .collect(Collectors.toList());
        return collect;
    }


    /**
     * 스터디 하나 조회 with app
     * */
    public StudyDto findStudyOne(Long postId){
        Study findStudy;
        Post findPost = qPostRepository.findPostWithAppById(postId);

        if(findPost instanceof Study) {
            findStudy = (Study)findPost;
            return new StudyDto(findPost.getTitle(),findPost.getContent(), findPost.getApplications());
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
