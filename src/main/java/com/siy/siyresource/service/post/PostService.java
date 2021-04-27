package com.siy.siyresource.service.post;

import com.siy.siyresource.domain.condition.PostSearchCondition;
import com.siy.siyresource.domain.dto.account.AccountDto;
import com.siy.siyresource.domain.dto.post.*;
import com.siy.siyresource.domain.dto.post.Linear.PostLinearDto;
import com.siy.siyresource.domain.dto.post.detail.CarFoolDtoDetail;
import com.siy.siyresource.domain.dto.post.detail.ContestDtoDetail;
import com.siy.siyresource.domain.dto.post.detail.PostDtoDetail;
import com.siy.siyresource.domain.dto.post.detail.StudyDtoDetail;
import com.siy.siyresource.domain.entity.accountPost.AccountPost;
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
    @Transactional
    public Long save(Post post) {
        Post save = postRepository.save(post);
        return save.getId();
    }

    /**
     * 포스트 전체 조회
     *
     * @return
     */
    public Page<PostDto> findPostList(int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);

        Page<PostDto> results = postRepository.findPostListWithPaging(page);

        return results;
    }

    /**
     * Study 전체 조회
     *
     * @return
     */
    public Page<StudyDto> findStudyList(int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);

        Page<StudyDto> results = postRepository.findStudyListWithPaging(page);
        return results;
    }

    /**
     * CarFull 전체 조회
     *
     * @return
     */
    public Page<CarFullDto> findCarFulList(int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);

        Page<CarFullDto> results = postRepository.findCarFullListWithPaging(page);
        return results;
    }

    /**
     * Contest 전체 조회
     *
     * @return
     */
    public Page<ContestDto> findContestList(int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);

        Page<ContestDto> results = postRepository.findContestListWithPaging(page);

        return results;
    }

    public PostDtoDetail findPostById(PostSearchCondition condition) {
        Post findPost = postRepository.findPostById(condition);


        Set<ApplicationDto> applications = getApplicationDtoList(findPost);

        Set<ParticipantsDto> participants = getParticipantsList(findPost.getAccountPosts());

        PostDtoDetail postDtoDetail = new PostDtoDetail(findPost, participants, applications);
        return postDtoDetail;
    }


    public PostDtoDetail findStudyById(PostSearchCondition condition) {
        Study findPost = (Study) postRepository.findPostById(condition);

        Set<ApplicationDto> applications = getApplicationDtoList(findPost);

        Set<ParticipantsDto> participants = getParticipantsList(findPost.getAccountPosts());

        StudyDtoDetail postDtoDetail = new StudyDtoDetail(findPost, participants, applications);
        return postDtoDetail;
    }


    public ContestDtoDetail findContestById(PostSearchCondition condition) {
        Contest findPost = (Contest) postRepository.findPostById(condition);

        Set<ApplicationDto> applications = getApplicationDtoList(findPost);

        Set<ParticipantsDto> participants = getParticipantsList(findPost.getAccountPosts());

        ContestDtoDetail postDtoDetail = new ContestDtoDetail(findPost, participants, applications);
        return postDtoDetail;
    }

    public CarFoolDtoDetail findCarFoolById(PostSearchCondition condition) {
        CarFull findPost = (CarFull) postRepository.findPostById(condition);

        Set<ApplicationDto> applications = getApplicationDtoList(findPost);

        Set<ParticipantsDto> participants = getParticipantsList(findPost.getAccountPosts());

        CarFoolDtoDetail postDtoDetail = new CarFoolDtoDetail(findPost, participants, applications);
        return postDtoDetail;
    }

    public Page<PostLinearDto> findPostLinearList(int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);

        Page<PostLinearDto> result = postRepository.findPostLinearListWithPaging(page);

        return result;
    }


    public Page<PostLinearDto> findParticipatingList(String username, int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);
        PostSearchCondition condition = new PostSearchCondition(null, null, username);

        Page<PostLinearDto> result = postRepository.findParticipatingPost(condition, page);

        return result;

    }

    public Post getPostEntity(PostSearchCondition condition) {
        Post findPost = postRepository.findPostById(condition);
        return findPost;

    }

    private Set<ApplicationDto> getApplicationDtoList(Post findPost) {
        return findPost.getApplications()
                .stream()
                .map(a -> new ApplicationDto(a.getId(), a.getContent(), a.getChatDate()))
                .collect(Collectors.toSet());
    }

    private Set<ParticipantsDto> getParticipantsList(Set<AccountPost> accountPosts) {
        return accountPosts
                .stream()
                .map(a -> new ParticipantsDto(a.getAccount().getUsername(), a.getAccount().getEmail(), a.getAccount().getAge(), a.getCode()))
                .collect(Collectors.toSet());
    }
}
