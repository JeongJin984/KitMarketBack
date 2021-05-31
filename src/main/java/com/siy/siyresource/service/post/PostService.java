package com.siy.siyresource.service.post;

import com.siy.siyresource.common.api.request.CreatePostRequest;
import com.siy.siyresource.domain.condition.PostSearchCondition;
import com.siy.siyresource.domain.dto.ApplicationDto;
import com.siy.siyresource.domain.dto.ParticipantsDto;
import com.siy.siyresource.domain.dto.detail.*;
import com.siy.siyresource.domain.dto.post.*;
import com.siy.siyresource.domain.dto.Linear.PostLinearDto;
import com.siy.siyresource.domain.entity.post.CarPool.CarPool;
import com.siy.siyresource.domain.entity.post.Contest.Contest;
import com.siy.siyresource.domain.entity.post.MiniProject;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.domain.entity.post.PostStatus;
import com.siy.siyresource.domain.entity.post.Study.Study;
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
    public Page<PostDto> findPostList(String status, int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);

        Page<PostDto> results = postRepository.findPostListWithPaging(status, page);

        return results;
    }

    /**
     * Study 전체 조회
     *
     * @return
     */
    public Page<PostDto> findStudyList(String status, int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);

        Page<PostDto> results = postRepository.findStudyListWithPaging(status, page);
        return results;
    }

    /**
     * CarPool 전체 조회
     *
     * @return
     */
    public Page<PostDto> findCarPoolList(String status, int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);

        Page<PostDto> results = postRepository.findCarPoolListWithPaging(status, page);
        return results;
    }

    /**
     * Contest 전체 조회
     *
     * @return
     */
    public Page<PostDto> findContestList(String status, int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);

        Page<PostDto> results = postRepository.findContestListWithPaging(status, page);

        return results;
    }



    public PostDtoDetail findPostById(PostSearchCondition condition) {
        Post findPost = postRepository.findPostById(condition);


        Set<ApplicationDto> applications = getApplicationDtoList(findPost);

        //Set<ParticipantsDto> participants = getParticipantsList(findPost.getParticipants());

        PostDtoDetail postDtoDetail = new PostDtoDetail(findPost, null, applications);
        return postDtoDetail;
    }


    public StudyDtoDetail findStudyById(PostSearchCondition condition) {
        Study findPost = (Study)postRepository.findPostById(condition);

        Set<ApplicationDto> applications = getApplicationDtoList(findPost);

        //Set<ParticipantsDto> participants = getParticipantsList(findPost.getParticipants());

        StudyDtoDetail postDtoDetail = new StudyDtoDetail(findPost, null, applications);
        return postDtoDetail;
    }


    public ContestDtoDetail findContestById(PostSearchCondition condition) {
        Contest findPost = (Contest) postRepository.findPostById(condition);

        Set<ApplicationDto> applications = getApplicationDtoList(findPost);

        //Set<ParticipantsDto> participants = getParticipantsList(findPost.getParticipants());

        ContestDtoDetail postDtoDetail = new ContestDtoDetail(findPost, null, applications);
        return postDtoDetail;
    }

    public CarPoolDtoDetail findCarFoolById(PostSearchCondition condition) {
        CarPool findPost = (CarPool) postRepository.findPostById(condition);

        Set<ApplicationDto> applications = getApplicationDtoList(findPost);

        //Set<ParticipantsDto> participants = getParticipantsList(findPost.getParticipants());

        CarPoolDtoDetail postDtoDetail = new CarPoolDtoDetail(findPost, null, applications);
        return postDtoDetail;
    }

    public MiniProjectDtoDetail findMiniProjectById(PostSearchCondition condition) {
        MiniProject findPost = (MiniProject)postRepository.findPostById(condition);
        Set<ApplicationDto> applications = getApplicationDtoList(findPost);

        //Set<ParticipantsDto> participants = getParticipantsList(findPost.getParticipants());
        MiniProjectDtoDetail miniProjectDtoDetail = new MiniProjectDtoDetail(findPost, null, applications);
        return miniProjectDtoDetail;
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

    private Set<ParticipantsDto> getParticipantsList(String participants) {
//        return accountPosts
//                .stream()
//                .map(a -> new ParticipantsDto(a.getUsername(), a.getEmail(), a.getAge().intValue())
//                .collect(Collectors.toSet());
        return null;
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

    @Transactional
    public void updatePostEntity(CreatePostRequest request, Post post) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime deadLine = LocalDateTime.parse(request.getDeadLine(), format);

        post.setWriter(request.getWriter());
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setDueDate(deadLine);
        post.setMaxNumber(request.getMaxNum());
        post.setCurrentNumber(request.getCurNum());
        post.setCategory(request.getCategory());
    }


    @Transactional
    public void updateContest(Long id, CreatePostRequest request) {
        Contest contest = postRepository.findById(id);

        updatePostEntity(request, contest);
    }
    @Transactional
    public void updateStudy(Long id, CreatePostRequest request) {
        Study study = postRepository.findById(id);
        updatePostEntity(request, study);
    }

    @Transactional
    public void updatecarFool(Long id, CreatePostRequest request) {
        CarPool carFool = postRepository.findById(id);
        updatePostEntity(request, carFool);
    }


    public Page<PostDto> findSearchList(String title, String username, String status, int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);
        PostSearchCondition condition = new PostSearchCondition(null, username, null, title, status);
        Page<PostDto> searchList = postRepository.findSearchList(condition, page);
        return searchList;

    }

    @Transactional
    public void operatingPost(Long id) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        Post findPost = postRepository.findPostById(condition);

        findPost.setPostStatus(PostStatus.OPERATING);
    }

    @Transactional
    public void closedPost(Long id) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        Post findPost = postRepository.findPostById(condition);

        findPost.setPostStatus(PostStatus.CLOSE);
    }


}
