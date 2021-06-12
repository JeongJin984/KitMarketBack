package com.siy.siyresource.service.post;

import com.siy.siyresource.common.api.request.*;
import com.siy.siyresource.domain.condition.PostSearchCondition;
import com.siy.siyresource.domain.dto.AllDetail.*;
import com.siy.siyresource.domain.dto.ClosedDetail.*;
import com.siy.siyresource.domain.dto.ApplicationDto;
import com.siy.siyresource.domain.dto.ParticipantsDetail;
import com.siy.siyresource.domain.dto.PostingDetail.*;
import com.siy.siyresource.domain.dto.RelatedUser;
import com.siy.siyresource.domain.dto.post.*;
import com.siy.siyresource.domain.dto.Linear.PostLinearDto;
import com.siy.siyresource.domain.entity.Participants;
import com.siy.siyresource.domain.entity.post.CarPool.CarPool;
import com.siy.siyresource.domain.entity.post.Contest.Contest;
import com.siy.siyresource.domain.entity.post.MiniProject;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.domain.entity.post.PostStatus;
import com.siy.siyresource.domain.entity.post.Study.Study;
import com.siy.siyresource.feign.UserServiceClient;
import com.siy.siyresource.repository.PostRepository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final CircuitBreakerFactory circuitBreakerFactory;
    private final UserServiceClient userServiceClient;


    /**
     * 1. post 전체 조회
     */
    public Page<PostDto> findPostList(String status, int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);

        Page<PostDto> results = postRepository.findPostListWithPaging(status, page);

        return results;
    }

    /**
     * 2. Study 전체 조회
     */
    public Page<PostDto> findStudyList(String status, int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);

        Page<PostDto> results = postRepository.findStudyListWithPaging(status, page);
        return results;
    }

    /**
     * 3. CarPool 전체 조회
     */
    public Page<PostDto> findCarPoolList(String status, int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);

        Page<PostDto> results = postRepository.findCarPoolListWithPaging(status, page);
        return results;
    }

    /**
     * 4. Contest 전체 조회
     */
    public Page<PostDto> findContestList(String status, int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);

        Page<PostDto> results = postRepository.findContestListWithPaging(status, page);

        return results;
    }

    /**
     * 5. MiniProjectList
     */
    public Page<PostDto> findMiniProjectList(String status, int offset, int size) {
        PageRequest page =PageRequest.of(offset, size);

        return postRepository.findMiniProjectListWithPaging(status, page);
    }

    /**
     * 5. Post 한개 조회
     */
    public PostDtoPostingDetail findPostById(PostSearchCondition condition) {
        Post findPost = postRepository.findPostById(condition);

        Set<ApplicationDto> applications = getApplicationDtoList(findPost);

        PostDtoPostingDetail postDtoPostingDetail = new PostDtoPostingDetail(findPost, applications);
        return postDtoPostingDetail;
    }



    /**
     * 6. Study 한개 조회
     */
    public StudyDtoPostingDetail findStudyById(PostSearchCondition condition) {
        Study findPost = (Study)postRepository.findPostById(condition);

        Set<ApplicationDto> applications = getApplicationDtoList(findPost);

        StudyDtoPostingDetail postDtoDetail = new StudyDtoPostingDetail(findPost,  applications);
        return postDtoDetail;
    }

    /**
     * 7.carFool 한개 조회
     */
    public CarPoolDtoPostingDetail findCarFoolById(PostSearchCondition condition) {
        CarPool findPost = (CarPool) postRepository.findPostById(condition);

        Set<ApplicationDto> applications = getApplicationDtoList(findPost);

        CarPoolDtoPostingDetail postDtoDetail = new CarPoolDtoPostingDetail(findPost, applications);
        return postDtoDetail;
    }

    /**
     * 8. contest 한개 조회
     */
    public ContestDtoPostingDetail findContestById(PostSearchCondition condition) {
        Contest findPost = (Contest)postRepository.findPostById(condition);

        Set<ApplicationDto> applications = getApplicationDtoList(findPost);

        ContestDtoPostingDetail postDtoDetail = new ContestDtoPostingDetail(findPost, applications);


        return postDtoDetail;
    }


    /**
     * 9. post 저장
     */
    @Transactional
    public Long postSave(CreatePostRequest request) {
        Post post = new Post();
        PostRequestToPostEntity(post, request);

        postRepository.save(post);
        return post.getId();
    }

    /**
     * 10. contest 저장
     * @param request
     */
    @Transactional
    public void contestSave(CreateContestRequest request) {
        Contest post = new Contest();
        PostRequestToContestEntity(post, request);

        postRepository.save(post);
    }

    /**
     * 11. carPool 저장
     * @param request
     */
    @Transactional
    public void carPoolSave(CreateCarPoolRequest request) {
        CarPool post = new CarPool();
        PostRequestToCarFoolEntity(post, request);

        postRepository.save(post);
    }

    /**
     * 12. study 저장
     * @param request
     */
    @Transactional
    public void studySave(CreateStudyRequest request) {
        Study study = new Study();
        PostRequestToStudyEntity(study, request);

        postRepository.save(study);
    }

    /**
     * 13. 미니 프로젝트 저장
     */
    public void studyMiniProject(CreateMiniProjectRequest request) {
        MiniProject miniProject = new MiniProject();
        PostRequestToMiniProject(miniProject ,request);

        postRepository.save(miniProject);
    }




    /**
     * 14. 내가만든 모임
     * */
    public Page<PostLinearDto> findPostListByUsername(PostSearchCondition condition, int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);
        Page<PostLinearDto> result = postRepository.findPostListByUsername(condition, page);
        return result;
    }

    /**
     * 15. 내가 참여하고 있는 모임
     * */
    public Page<PostLinearDto> findParticipatingList(String username, int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);
        PostSearchCondition condition = new PostSearchCondition(null, null, username);

        Page<PostLinearDto> result = postRepository.findParticipatingPost(condition, page);

        return result;

    }

    /**
     * 16. 내가 신청 대기중인 모임현황
     * */
    public Page<PostLinearDto> findPostListByApplicationUserName(PostSearchCondition condition, int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);
        Page<PostLinearDto> result = postRepository.findPostListByApplicationUserName(condition, page);

        return result;
    }

    /**
     * 17. 게시글 삭제하기
     * */
    @Transactional
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }


    /**
     * 18. post 수정하기
     * @param id
     * @param request
     */
    @Transactional
    public void updatePost(Long id, CreatePostRequest request) {
        Post post = postRepository.findById(id);

        PostRequestToPostEntity(post, request);
    }

    /**
     * 19. contest 수정하기
     * @param id
     * @param request
     */
    @Transactional
    public void updateContest(Long id, CreateContestRequest request) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        Contest contest = (Contest)postRepository.findPostById(condition);

        PostRequestToContestEntity((Contest) contest, request);
    }

    /**
     * 20. Study 수정하기
     * @param id
     * @param request
     */
    @Transactional
    public void updateStudy(Long id, CreateStudyRequest request) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        Study study = (Study)postRepository.findPostById(condition);

        PostRequestToStudyEntity(study, request);
    }


    /**
     * 21. CarPool 수정하기
     * @param id
     * @param request
     */
    @Transactional
    public void updateCarFool(Long id, CreateCarPoolRequest request) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        CarPool carPool = (CarPool)postRepository.findPostById(condition);
        PostRequestToCarFoolEntity(carPool, request);
    }

    /**
     * miniProject 수정하기
     * @param id
     * @param request
     */
    public void updateMiniProject(Long id, CreateMiniProjectRequest request) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        MiniProject miniProject = (MiniProject)postRepository.findPostById(condition);
        PostRequestToMiniProject(miniProject, request);

    }


    /**
     * 22. 검색 기능
     * @param title
     * @param username
     * @param status
     * @param offset
     * @param size
     * @return
     */
    public Page<PostDto> findSearchList(String title, String username, String status, int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);
        PostSearchCondition condition = new PostSearchCondition(null, username, null, title, status);
        Page<PostDto> searchList = postRepository.findSearchList(condition, page);
        return searchList;
    }




    /**
     * 23. 포스트 운영 종료하기
     * @param id
     */
    @Transactional
    public void closedPost(Long id) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        Post findPost = postRepository.findPostById(condition);

        findPost.setPostStatus(PostStatus.CLOSE);
    }

    /**
     *  참석자 포함 포스트 보기
     */
    public PostDtoClosedDetail findPostWithParticipants(Long id) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        Post findPost = postRepository.findPostById(condition);

        Set<ParticipantsDetail> participants = getParticipants(findPost);

        return new PostDtoClosedDetail(findPost, participants);
    }

    /**
     *  참석자 포함 포스트 보기
     */
    public StudyDtoClosedDetail findStudyWithParticipants(Long id) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        Study findPost = (Study)postRepository.findPostById(condition);

        Set<ParticipantsDetail> participants = getParticipants(findPost);

        return new StudyDtoClosedDetail(findPost, participants);
    }


    public CarPoolDtoClosedDetail findcarPoolWithParticipants(Long id) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        CarPool findPost = (CarPool)postRepository.findPostById(condition);

        Set<ParticipantsDetail> participants = getParticipants(findPost);

        return new CarPoolDtoClosedDetail(findPost, participants);
    }

    public ContestDtoClosedDetail findContestWithParticipants(Long id) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        Contest findPost = (Contest)postRepository.findPostById(condition);

        Set<ParticipantsDetail> participants = getParticipants(findPost);

        return new ContestDtoClosedDetail(findPost, participants);
    }

    public MiniProjectDtoClosedDetail findMiniProjectWithParticipants(Long id) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        MiniProject findPost = (MiniProject)postRepository.findPostById(condition);

        Set<ParticipantsDetail> participants = getParticipants(findPost);

        return new MiniProjectDtoClosedDetail(findPost, participants);
    }

    public PostDtoAllDetail findPostWithAppAndPart(Long id) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        Post post = postRepository.findPostById(condition);

        Set<ApplicationDto> applications = getApplicationDtoList(post);
        Set<ParticipantsDetail> participants = getParticipants(post);

        PostDtoAllDetail postDtoAllDetail = new PostDtoAllDetail(post, applications, participants);
        return postDtoAllDetail;
    }
    public PostDtoAllDetail findCarPoolWithAppAndPart(Long id) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        CarPool post = (CarPool)postRepository.findPostById(condition);

        Set<ApplicationDto> applications = getApplicationDtoList(post);
        Set<ParticipantsDetail> participants = getParticipants(post);

        CarPoolDtoAllDetail postDtoAllDetail = new CarPoolDtoAllDetail(post, applications, participants);
        return postDtoAllDetail;
    }

    public PostDtoAllDetail findStudyWithAppAndPart(Long id) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        Study post = (Study)postRepository.findPostById(condition);

        Set<ApplicationDto> applications = getApplicationDtoList(post);
        Set<ParticipantsDetail> participants = getParticipants(post);

        StudyDtoAllDetail postDtoAllDetail = new StudyDtoAllDetail(post, applications, participants);
        return postDtoAllDetail;
    }
    public PostDtoAllDetail findContestWithAppAndPart(Long id) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        Contest post = (Contest)postRepository.findPostById(condition);

        Set<ApplicationDto> applications = getApplicationDtoList(post);
        Set<ParticipantsDetail> participants = getParticipants(post);

        ContestDtoAllDetail postDtoAllDetail = new ContestDtoAllDetail(post, applications, participants);
        return postDtoAllDetail;
    }
    public PostDtoAllDetail findMiniProjectWithAppAndPart(Long id) {
        PostSearchCondition condition = new PostSearchCondition(id, null, null);
        MiniProject post = (MiniProject)postRepository.findPostById(condition);

        Set<ApplicationDto> applications = getApplicationDtoList(post);
        Set<ParticipantsDetail> participants = getParticipants(post);

        MiniProjectDtoAllDetail postDtoAllDetail = new MiniProjectDtoAllDetail(post, applications, participants);
        return postDtoAllDetail;
    }










    public Page<PostLinearDto> findPostLinearList(int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);

        Page<PostLinearDto> result = postRepository.findPostLinearListWithPaging(page);

        return result;
    }







    public Post getPostEntity(PostSearchCondition condition) {
        Post findPost = postRepository.findPostById(condition);
        return findPost;
    }



    private Set<ApplicationDto> getApplicationDtoList(Post findPost) {
        return findPost.getApplications()
                .stream()
                .map(a -> new ApplicationDto(a.getId(), a.getContent(), a.getCreatedAt(),a.getUsername()))
                .collect(Collectors.toSet());
    }

    private Set<ParticipantsDetail> getParticipants(Post findPost) {

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");

        Set<Participants> participants = findPost.getParticipants();

        Set<ParticipantsDetail> participantsDetails = new HashSet<ParticipantsDetail>();

        for (Participants username:  participants) {
            log.info("Before call user-service for participants");
            RelatedUser findUser = circuitBreaker.run(() -> userServiceClient.getUserData(username.getUsername())
                    , throwable -> new RelatedUser());
            log.info("After call post-service for createdPosts");

            participantsDetails.add(
                    new ParticipantsDetail(findUser.getUsername(), findUser.getEmail(),
                    findPost.getWriter().equals(username.getUsername()) ? "Leader":"Member"
                )
            );
        }

        return participantsDetails;

    }

    private Set<ParticipantsDetail> getParticipantsList(Post findPost) {
        return null;
    }


    public MiniProjectDtoPostingDetail findMiniProjectById(PostSearchCondition condition) {
        MiniProject findPost = (MiniProject)postRepository.findPostById(condition);
        Set<ApplicationDto> applications = getApplicationDtoList(findPost);

        MiniProjectDtoPostingDetail miniProjectDtoDetail = new MiniProjectDtoPostingDetail(findPost, applications);
        return miniProjectDtoDetail;
    }

    public Page<PostLinearDto> findPostListByParticipants(PostSearchCondition condition, int offset, int size) {
        PageRequest page = PageRequest.of(offset, size);
        Page<PostLinearDto> result = postRepository.findParticipatingPost(condition, page);
        return result;

    }


    private void PostRequestToStudyEntity(Study study, CreateStudyRequest request) {
        PostRequestToPostEntity(study, request);

        study.setCategory("study");

        study.setSubject(study.stringToSubject(request.getSubject()));
        study.setRegion(request.getRegion());
        study.setDuration(request.getDuration());

        System.out.println("study = " + study);
    }

    private void PostRequestToCarFoolEntity(CarPool carPool, CreateCarPoolRequest request) {
        PostRequestToPostEntity(carPool, request);
        carPool.setCategory("carPool");

        carPool.setQualifyGender(carPool.stringToGender(request.getGender()));
        carPool.setFare(Long.valueOf(request.getFare()));
        carPool.setDepartHours(request.getDepartTime().getHours());
        carPool.setDepartMinutes(request.getDepartTime().getMinutes());
        carPool.setDestination(request.getDestination());
        carPool.setLong_(request.getLong());
        carPool.setLat(request.getLat());

        System.out.println("carPool = " + carPool);
    }


    private void PostRequestToContestEntity(Contest contest,  CreateContestRequest request) {
        PostRequestToPostEntity(contest, request);
        contest.setCategory("contest");

        contest.setContestCategory(contest.stringToContestCategory(request.getContestCategory()));
        contest.setHostOrganization(request.getHostOrganization());
        contest.setQualification(contest.stringToQualification(request.getQualification()));
        contest.setHomepage(request.getHomepage());

        System.out.println("contest = " + contest);
    }
    private void PostRequestToMiniProject(MiniProject miniProject, CreateMiniProjectRequest request) {
        PostRequestToPostEntity(miniProject, request);

        miniProject.setCategory("miniProject");
        miniProject.setProjectDuration(request.getProjectDuration());
        miniProject.setTopic(request.getTopic());

        System.out.println("miniProject = " + miniProject);
    }

    private LocalDateTime getLocalDateTime(String departTime2) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(departTime2, format);
    }

    private Post PostRequestToPostEntity(Post post, CreatePostRequest request) {
        System.out.println("request.getDeadLine() = " + request.getDeadLine());
        LocalDateTime deadLine = getLocalDateTime(request.getDeadLine());

        post.setWriter(request.getWriter());
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setDueDate(deadLine);
        post.setMaxNumber(request.getMaxNum());
        post.setCurrentNumber(request.getCurNum());
        post.setPostStatus(PostStatus.POSTING);
        post.setCategory("post");


        return post;
    }



}
