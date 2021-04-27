package com.siy.siyresource.service.post;

import com.siy.siyresource.domain.dto.account.AccountDto;
import com.siy.siyresource.domain.dto.post.*;
import com.siy.siyresource.domain.dto.post.detail.CarFoolDtoDetail;
import com.siy.siyresource.domain.dto.post.detail.ContestDtoDetail;
import com.siy.siyresource.domain.dto.post.detail.PostDtoDetail;
import com.siy.siyresource.domain.dto.post.detail.StudyDtoDetail;
import com.siy.siyresource.domain.entity.post.CarFull;
import com.siy.siyresource.domain.entity.post.Contest;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.domain.entity.post.Study;
import com.siy.siyresource.repository.PostRepository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

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

    public PostDtoDetail findPostById(Long id) {
        Post findPost = postRepository.findPostById(id);


        Set<ApplicationDto> applications = findPost.getApplications()
                .stream()
                .map(a -> new ApplicationDto(a.getId(), a.getContent(), a.getChatDate()))
                .collect(Collectors.toSet());

        Set<AccountDto> participants = findPost.getAccountPosts()
                .stream()
                .map(a -> new AccountDto(a.getAccount().getUsername(), a.getAccount().getEmail(), a.getAccount().getAge()))
                .collect(Collectors.toSet());

        PostDtoDetail postDtoDetail = new PostDtoDetail(findPost, participants, applications);
        return postDtoDetail;
    }


    public PostDtoDetail findStudyById(Long id) {
        Study findPost = (Study)postRepository.findPostById(id);

        Set<ApplicationDto> applications = findPost.getApplications()
                .stream()
                .map(a -> new ApplicationDto(a.getId(), a.getContent(), a.getChatDate()))
                .collect(Collectors.toSet());

        Set<AccountDto> participants = findPost.getAccountPosts()
                .stream()
                .map(a -> new AccountDto(a.getAccount().getUsername(), a.getAccount().getEmail(), a.getAccount().getAge()))
                .collect(Collectors.toSet());

        StudyDtoDetail postDtoDetail = new StudyDtoDetail(findPost, participants, applications);
        return postDtoDetail;
    }


    public ContestDtoDetail findContestById(Long id) {
        Contest findPost = (Contest)postRepository.findPostById(id);

        Set<ApplicationDto> applications = findPost.getApplications()
                .stream()
                .map(a -> new ApplicationDto(a.getId(), a.getContent(), a.getChatDate()))
                .collect(Collectors.toSet());

        Set<AccountDto> participants = findPost.getAccountPosts()
                .stream()
                .map(a -> new AccountDto(a.getAccount().getUsername(), a.getAccount().getEmail(), a.getAccount().getAge()))
                .collect(Collectors.toSet());

        ContestDtoDetail postDtoDetail = new ContestDtoDetail(findPost, participants, applications);
        return postDtoDetail;
    }

    public CarFoolDtoDetail findCarFoolById(Long id) {
        CarFull findPost = (CarFull)postRepository.findPostById(id);

        Set<ApplicationDto> applications = findPost.getApplications()
                .stream()
                .map(a -> new ApplicationDto(a.getId(), a.getContent(), a.getChatDate()))
                .collect(Collectors.toSet());

        Set<AccountDto> participants = findPost.getAccountPosts()
                .stream()
                .map(a -> new AccountDto(a.getAccount().getUsername(), a.getAccount().getEmail(), a.getAccount().getAge()))
                .collect(Collectors.toSet());

        CarFoolDtoDetail postDtoDetail = new CarFoolDtoDetail(findPost, participants, applications);
        return postDtoDetail;
    }
}
