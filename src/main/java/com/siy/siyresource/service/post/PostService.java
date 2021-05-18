package com.siy.siyresource.service.post;

import com.siy.siyresource.common.api.request.CreatePostRequest;
import com.siy.siyresource.domain.condition.PostSearchCondition;
import com.siy.siyresource.domain.dto.post.*;
import com.siy.siyresource.domain.dto.post.Linear.PostLinearDto;
import com.siy.siyresource.domain.dto.post.detail.CarPoolDtoDetail;
import com.siy.siyresource.domain.dto.post.detail.ContestDtoDetail;
import com.siy.siyresource.domain.dto.post.detail.PostDtoDetail;
import com.siy.siyresource.domain.dto.post.detail.StudyDtoDetail;
import com.siy.siyresource.domain.entity.accountPost.AccountPost;
import com.siy.siyresource.domain.entity.post.CarPool;
import com.siy.siyresource.domain.entity.post.Contest.Contest;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.domain.entity.post.Study;
import com.siy.siyresource.repository.PostRepository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
     * CarPool 전체 조회
     *
     * @return
     */
    public Page<CarPoolDto> findCarPoolList(int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);

        Page<CarPoolDto> results = postRepository.findCarPoolListWithPaging(page);
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

    public CarPoolDtoDetail findCarFoolById(PostSearchCondition condition) {
        CarPool findPost = (CarPool) postRepository.findPostById(condition);

        Set<ApplicationDto> applications = getApplicationDtoList(findPost);

        Set<ParticipantsDto> participants = getParticipantsList(findPost.getAccountPosts());

        CarPoolDtoDetail postDtoDetail = new CarPoolDtoDetail(findPost, participants, applications);
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

    public Contest getContestEntity(PostSearchCondition condition) {
        Contest contest = postRepository.findContestById(condition);
        return contest;
    }


    private Set<ApplicationDto> getApplicationDtoList(Post findPost) {
        return findPost.getApplications()
                .stream()
                .map(a -> new ApplicationDto(a.getId(), a.getContent(), a.getCreatedAt(),a.getUsername()))
                .collect(Collectors.toSet());
    }

    private Set<ParticipantsDto> getParticipantsList(Set<AccountPost> accountPosts) {
        return accountPosts
                .stream()
                .map(a -> new ParticipantsDto(a.getAccount().getUsername(), a.getAccount().getEmail(), a.getAccount().getAge(), a.getCode()))
                .collect(Collectors.toSet());
    }

    public Page<PostLinearDto> findPostListByUsername(PostSearchCondition condition, int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);
        Page<PostLinearDto> result = postRepository.findPostListByUsername(condition, page);
        return result;
    }

    public Page<PostLinearDto> findPostListByApplicationUserName(PostSearchCondition condition, int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);
        Page<PostLinearDto> result = postRepository.findPostListByApplicationUserName(condition, page);

        return result;
    }

    public Page<PostLinearDto> findPostListByParticipants(PostSearchCondition condition, int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);
        Page<PostLinearDto> result = postRepository.findParticipatingPost(condition, page);
        return result;

    }

    @Transactional
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public void updatePost(Long id, CreatePostRequest request) {
        Post post = postRepository.findById(id);
        updatePostEntity(request, post);
    }

    private void updatePostEntity(CreatePostRequest request, Post post) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime deadLine = LocalDateTime.parse(request.getDeadLine(), format);

        post.setWriter(request.getWriter());
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setDeadLine(deadLine);
        post.setMaxNumber(request.getMaxNum());
        post.setCurrentNumber(request.getCurNum());
        post.setCategory(request.getCategory());
    }



    public void updateContest(Long id, CreatePostRequest request) {
        Contest contest = postRepository.findById(id);

        updatePostEntity(request, contest);
    }

    public void updateStudy(Long id, CreatePostRequest request) {
        Study study = postRepository.findById(id);
        updatePostEntity(request, study);
    }


    public void updatecarFool(Long id, CreatePostRequest request) {
        CarPool carFool = postRepository.findById(id);
        updatePostEntity(request, carFool);
    }
}
